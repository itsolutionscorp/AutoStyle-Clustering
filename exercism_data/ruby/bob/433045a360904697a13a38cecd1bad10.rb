class Bob

    def hey(phrase)
        case
            when silent?(phrase)
                "Fine. Be that way!"
            when yelling?(phrase)
                "Woah, chill out!"
            when question?(phrase)
                "Sure."
            else
                "Whatever."
            end
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
