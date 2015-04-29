class Bob

    RESPONSES = { 
        question: "Sure.",
        silent:  "Fine. Be that way!",
        yelling:  "Woah, chill out!",
        default:  "Whatever."
    }

    def hey(phrase)
        result = case
        when silent?(phrase)
            :silent
        when yelling?(phrase)
            :yelling
        when question?(phrase)
            :question
        else
            :default
        end
        RESPONSES[result]
    end

    private

    def yelling?(phrase)
        not silent?(phrase) and phrase == phrase.upcase
    end

    def question?(phrase)
        phrase.end_with? "?"
    end

    def silent?(phrase)
        phrase.strip.empty?
    end

end
