def silence?(message)
    !message || message.strip.empty?
end

def shouting?(message)
    message.upcase.eql?(message)
end

def question?(message)
    message.end_with?('?')
end

class Bob
    def hey(message)
        case
            when silence?(message); 'Fine. Be that way!'
            when shouting?(message); 'Woah, chill out!'
            when question?(message); 'Sure.'
            else 'Whatever.'
        end
    end
end
