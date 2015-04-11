class Bob

    def hey(message)
        message.strip!
        if message.empty?
            'Fine. Be that way!'
        elsif upcase? message 
            'Woah, chill out!'
        elsif question? message 
            'Sure.'
        else
            'Whatever.'
        end

    end

    private 

    def upcase?(message)
        message.upcase == message && message.downcase != message
    end

    def question?(message)
        message[-1] == '?'
    end


end
