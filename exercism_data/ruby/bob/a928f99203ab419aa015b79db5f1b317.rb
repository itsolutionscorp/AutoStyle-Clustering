class Bob
    def hey(message)
        clean_message = message.strip
        if message_is_silent? clean_message
            'Fine. Be that way!'
        elsif message_is_shouted? clean_message
            'Woah, chill out!'
        elsif message_is_question? clean_message
            'Sure.'
        else
            'Whatever.'
        end
    end
    
    def message_is_silent?(message)
        message == ''
    end
    
    def message_is_shouted?(message)
        message.upcase == message and message.downcase != message
    end
    
    def message_is_question?(message)
        message.end_with?('?')
    end
end
