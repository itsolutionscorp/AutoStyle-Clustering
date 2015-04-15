class Bob
    def hey(input)
        words = trim(input)

        if (silence? words)
            'Fine. Be that way!'
        elsif (yelling? words)
            'Woah, chill out!'
        elsif (question? words)
            'Sure.'
        else
            'Whatever.'
        end
    end

    private
    def trim(input)
        (input || '').delete(' ')
    end

    def silence?(words)
        words.empty?
    end

    def yelling?(words)
        words.upcase == words
    end

    def question?(words)
        words.end_with?('?')
    end
end
