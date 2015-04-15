#!/usr/bin/env ruby


class Bob
    def hey(message)
        if message.strip == ''
            'Fine. Be that way!'
        elsif message.upcase == message and message.downcase != message
            'Woah, chill out!'
        elsif message.end_with?('?')
            'Sure.'
        else
            'Whatever.'
        end
    end
end
