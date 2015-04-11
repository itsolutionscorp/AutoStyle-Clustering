class Bob

    YELL_RESPONE = "Woah, chill out!"
    QUERY_RESPONSE = "Sure."
    SILENCE_RESPONSE = "Fine. Be that way!"
    DEFAULT_RESPONSE = "Whatever."


    def hey(greeting)
        if shouting?(greeting)
            YELL_RESPONE
        elsif question?(greeting)
            QUERY_RESPONSE
        elsif silence?(greeting)
            SILENCE_RESPONSE
        else
            DEFAULT_RESPONSE
        end
    end

    private

    # Tests

    def shouting?(str)
        str.upcase == str && contains_chars?(str)
    end

    def question?(str)
        str.end_with?('?')
    end

    def silence?(str)
        str.strip.empty?
    end

    def contains_chars?(str)
        str.match(/[a-z]/) or str.match(/[A-Z]/)
    end
end
