package eu.lenithia.lenroot.commands;

import eu.lenithia.lenroot.LenRoot;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.caption.Caption;
import org.incendo.cloud.caption.CaptionVariable;
import org.incendo.cloud.caption.StandardCaptionKeys;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.context.CommandInput;
import org.incendo.cloud.exception.parsing.ParserException;
import org.incendo.cloud.parser.ArgumentParseResult;
import org.incendo.cloud.parser.ArgumentParser;
import org.incendo.cloud.parser.ParserDescriptor;
import org.incendo.cloud.suggestion.SuggestionProvider;

import java.util.UUID;

public class ModuleParser<C> implements ArgumentParser<C, String> {

    static LenRoot lenRoot;

    public static void setInstance(LenRoot lenRoot) {
        ModuleParser.lenRoot = lenRoot;
    }

    @Override
    public  @NonNull ArgumentParseResult<@NonNull String> parse(
            @NonNull CommandContext<@NonNull C> commandContext,
            @NonNull CommandInput commandInput) {
        final String input = commandInput.peekString();
        if (lenRoot.getLenFeatureAPI().getRegisteredLenFeatureNames().contains(input)) {
            commandInput.readString();
            return ArgumentParseResult.success(input);
        } else {
            return ArgumentParseResult.failure(new ModuleParserException(ModuleParser.class, commandContext, StandardCaptionKeys.ARGUMENT_PARSE_FAILURE_STRING, new CaptionVariable[]{CaptionVariable.of("input", input)}));
        }
    }

    public static <C> @NonNull ParserDescriptor<C, UUID> moduleParser() {
        return ParserDescriptor.of(new ModuleParser(), UUID.class);
    }

    @Override
    public @NonNull SuggestionProvider<C> suggestionProvider() {
        return SuggestionProvider.blockingStrings((ctx, input) -> {
            return lenRoot.getLenFeatureAPI().getRegisteredLenFeatureNames();
        });
    }

    public static final class ModuleParserException extends ParserException {

        public ModuleParserException(Class<?> clazz, CommandContext<?> context, Caption caption, CaptionVariable... variables) {
            super(clazz, context, caption, variables);
        }
    }


}

