# Module designed to be included in a string.
module Sentence
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

class Object
    # Patch object to get a more fluent syntax.
    alias_method :as, :extend
end

class Bob

    YELL_RESPONSE = "Woah, chill out!"
    QUERY_RESPONSE = "Sure."
    SILENCE_RESPONSE = "Fine. Be that way!"
    DEFAULT_RESPONSE = "Whatever."

    def hey(greeting)
        respond_to(greeting.as(Sentence))
    end

    private 
    
    def respond_to(sentence)
        return YELL_RESPONSE if sentence.shouting?
        return QUERY_RESPONSE if sentence.question?
        return SILENCE_RESPONSE if sentence.silence?
        DEFAULT_RESPONSE
    end

end
