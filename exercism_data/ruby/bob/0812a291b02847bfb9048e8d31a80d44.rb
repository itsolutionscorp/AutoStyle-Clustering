#!/usr/bin/env ruby
#Bob exercism assignment

class Spoken
    def initialize(sentence)
        @sentence = String(sentence)
    end

    def yelling?
        !@sentence.empty? && @sentence == @sentence.upcase
    end

    def asking?
        @sentence.end_with?("?")
    end

    def unspoken?
        @sentence.empty?
    end
end

class Bob
    def hey(sentence)
        speak = Spoken.new(sentence)
        case 
        when speak.yelling?
            "Woah, chill out!"
        when speak.asking?
            "Sure."
        when speak.unspoken?
            "Fine. Be that way!"
        else
            "Whatever."
        end
    end
end
