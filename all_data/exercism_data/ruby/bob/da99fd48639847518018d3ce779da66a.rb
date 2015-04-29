class Bob
    def hey(sentence)
        if is_silence? sentence
            return "Fine. Be that way!"
        end
        if is_shouting? sentence
            return "Woah, chill out!"
        end
        if is_question? sentence
            return "Sure."
        end
        return "Whatever."
    end
    private
    def is_silence?(sentence)
        return sentence.lstrip.empty?
    end
    def is_shouting?(sentence)
        return sentence.upcase == sentence
    end
    def is_question?(sentence)
        return sentence.end_with? "?"
    end
end
