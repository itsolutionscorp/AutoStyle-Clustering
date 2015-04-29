class String
    def shouting?
        upcase == self && (match(/[a-z]/) || match(/[A-Z]/))
    end
    def question?
        end_with? '?'
    end
    def silence?
        strip.empty?
    end
end

class Bob

    YELL_RESPONE = "Woah, chill out!"
    QUERY_RESPONSE = "Sure."
    SILENCE_RESPONSE = "Fine. Be that way!"
    DEFAULT_RESPONSE = "Whatever."


    def hey(greeting)
        return YELL_RESPONE if greeting.shouting?
        return QUERY_RESPONSE if greeting.question?
        return SILENCE_RESPONSE if greeting.silence?
        DEFAULT_RESPONSE
    end

end
