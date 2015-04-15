class Bob
    def hey(message)
        if (not message.upcase!) and (message =~/[A-Za-z]/)!=nil
            'Woah, chill out!'
        elsif message.end_with?('?')
            'Sure.'
        elsif message.strip()=~/^\s*$/
            'Fine. Be that way!'
        else
            'Whatever.'
        end
    end
end
