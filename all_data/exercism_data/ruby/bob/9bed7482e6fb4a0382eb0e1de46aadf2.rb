class Bob

    def hey(message)
        message.strip!
        if message == ''
            'Fine. Be that way!'
        elsif message.upcase == message && message.downcase != message
            'Woah, chill out!'
        elsif message[-1] == '?'
            'Sure.'
        else
            'Whatever.'
        end

    end

end
