class Bob
    def hey(message)
        message.strip!

        if message.empty?
            return 'Fine. Be that way!'
        end

        if message =~ /[a-zA-Z]/ and message == message.upcase
            return 'Woah, chill out!'
        end

        if message[-1] == '?'
            return 'Sure.'
        end

        return 'Whatever.'
    end
end
