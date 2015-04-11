#!/usr/bin/env ruby

class Bob

    def hey phrase
        @phrase = phrase.strip
        case
        when nothing?
            "Fine. Be that way!"
        when yelling?
            "Woah, chill out!"
        when question?
            "Sure."
        else
            "Whatever."
        end
    end

    private

    def yelling?
        @phrase.eql? @phrase.upcase
    end

    def question?
        @phrase.end_with? "?"
    end

    def nothing? 
        @phrase.eql? ""
    end

end
