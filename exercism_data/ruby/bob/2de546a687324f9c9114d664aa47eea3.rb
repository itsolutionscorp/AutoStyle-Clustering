class Bob
    def hey(inputString)
        if isSilence(inputString)
            return 'Fine. Be that way!'
        end
        if isShouting(inputString)
            return 'Woah, chill out!'
        end
        if isQuestion(inputString)
            return 'Sure.'
        end
        return 'Whatever.'
    end

    private

    def isShouting(inputString)
        return inputString.upcase == inputString
    end

    def isQuestion(inputString)
        return inputString.end_with?('?')
    end

    def isSilence(inputString)
        return '' == inputString.strip
    end
end
