class Bob
    def hey(sentence)
        if silence? sentence
            return "Fine. Be that way!"
        end
        if shouting? sentence
            return "Woah, chill out!"
        end
        if question? sentence
            return "Sure."
        end
        "Whatever."
    end
    private
    def silence?(sentence)
        sentence.lstrip.empty?
    end
    def shouting?(sentence)
        sentence.upcase == sentence
    end
    def question?(sentence)
        sentence.end_with? "?"
    end
end
