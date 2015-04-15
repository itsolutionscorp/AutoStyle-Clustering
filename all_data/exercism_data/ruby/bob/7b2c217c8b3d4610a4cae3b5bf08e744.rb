#!/usr/bin/env ruby

class Bob

    @@responses = { 
        question: "Sure.",
        silence:  "Fine. Be that way!",
        yelling:  "Woah, chill out!",
        default:  "Whatever."
    }

    def hey(phrase)
        case
        when silence?(phrase)
            @@responses[:silence]
        when yelling?(phrase)
            @@responses[:yelling]
        when question?(phrase)
            @@responses[:question]
        else
            @@responses[:default]
        end
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
