#!/usr/bin/env ruby

class Bob

    def hey phrase
        if nothing phrase
            "Fine. Be that way!"
        elsif yelling phrase
            "Woah, chill out!"
        elsif question phrase
            "Sure."
        else
            "Whatever."
        end
    end

    private

    def yelling phrase
        return phrase.eql? phrase.upcase
    end

    def question phrase
        return phrase.end_with? "?"
    end

    def nothing phrase
        return phrase.gsub(/\s+/, "").eql? ""
    end

end
