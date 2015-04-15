class Bob
    def hey(input)
        case
        when input.strip.empty?
            'Fine. Be that way!'
        when shouting?(input)
            'Woah, chill out!'
        when questionning?(input)
            'Sure.'
        else
            'Whatever.'
        end
    end

    def shouting?(input)
        input.upcase == input
    end

    def questionning?(input)
        input.end_with?('?')
    end
end
