#!/usr/bin/env ruby

class Bob

    def initialize
        @responses = { 
            question: "Sure.",
            silence: "Fine. Be that way!",
            yelling: "Woah, chill out!",
            default: "Whatever."
        }
    end

    def hey phrase
        @phrase = phrase.strip
        case
        when nothing?
            @responses[:silence]
        when yelling?
            @responses[:yelling]
        when question?
            @responses[:question]
        else
            @responses[:default]
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
        @phrase.empty?
    end

end
