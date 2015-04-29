class Bob
    def hey message
        if message.strip.empty?
            return 'Fine. Be that way!'
        end
        if message.upcase == message
            return 'Woah, chill out!'
        end
        if message[-1] == '?'
            return 'Sure.'
        end
        return 'Whatever.'
    end
end
