class BobsMessage
    def initialize(message)
        @message = message.strip
    end

    def empty?
        @message.empty?
    end

    def upcase?
        @message.upcase == @message && @message.downcase != @message
    end

    def question?
        @message[-1] == '?'
    end
end


class Bob

    def hey(input)
        message = ::BobsMessage.new input

        if message.empty?
            'Fine. Be that way!'
        elsif message.upcase? 
            'Woah, chill out!'
        elsif message.question? 
            'Sure.'
        else
            'Whatever.'
        end
    end

end
