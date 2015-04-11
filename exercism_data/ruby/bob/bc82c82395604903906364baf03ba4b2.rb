class Bob
    def hey message
        case
        when message.strip.empty?
            return 'Fine. Be that way!'
        when message.upcase == message
            return 'Woah, chill out!'
        when message[-1] == '?'
            return 'Sure.'
        else
            return 'Whatever.'
        end
    end
end
