#!/usr/bin/env ruby

class Bob

    @@responses = { 
        question: "Sure.",
        silence:  "Fine. Be that way!",
        yelling:  "Woah, chill out!",
        default:  "Whatever."
    }

    def hey(phrase)
        result = :default
        case
        when silence?(phrase)
            result = :silence
        when yelling?(phrase)
            result = :yelling
        when question?(phrase)
            result = :question
        end

        @@responses[result]
    end

    private

    def yelling?(phrase)
        phrase.eql? phrase.upcase
    end

    def question?(phrase)
        phrase.end_with? "?"
    end

    def silence?(phrase)
        phrase.strip.empty?
    end

end
