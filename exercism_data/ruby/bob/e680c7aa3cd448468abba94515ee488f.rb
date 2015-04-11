#bob.rb
class Bob
    def hey(message)
        if message.nil? or message.empty?
            'Fine. Be that way!'
        elsif message == message.upcase
            'Woah, chill out!'
        elsif message.end_with?('?')
            'Sure.'
        else
            'Whatever.'
        end
    end
end
