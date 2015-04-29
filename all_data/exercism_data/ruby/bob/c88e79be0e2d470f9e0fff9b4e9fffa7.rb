class Bob
    def hey(sentence)
        if silence? sentence
            "Fine. Be that way!"
        end
        if shouting? sentence
            "Woah, chill out!"
        end
        if question? sentence
            "Sure."
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
