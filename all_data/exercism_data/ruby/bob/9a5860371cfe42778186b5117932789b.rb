class Bob

    # Conversational responses.
    def hey(message)
        # Remove all line endings from message.
        message.gsub!(/(\n)/, ' ')
        
        if message.strip == ''
            # Empty message
            'Fine. Be that way!'
        elsif message.upcase == message and message =~ /[A-Z]/
            # Shout message
            'Woah, chill out!'
        elsif message.chomp =~ /\?$/
            # Message is a question
            'Sure.'
        else
            # Default response
            'Whatever.'        
        end
    end

end
