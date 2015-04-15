class Bob
    def hey(inputString)
        if is_silence?(inputString)
            'Fine. Be that way!'
        elsif is_shouting?(inputString)
            'Woah, chill out!'
        elsif is_question?(inputString)
            'Sure.'
	else
            'Whatever.'
        end
    end

    private

    def is_shouting?(inputString)
        inputString.upcase == inputString
    end

    def is_question?(inputString)
        inputString.end_with?('?')
    end

    def is_silence?(inputString)
        inputString.strip.empty?
    end
end
