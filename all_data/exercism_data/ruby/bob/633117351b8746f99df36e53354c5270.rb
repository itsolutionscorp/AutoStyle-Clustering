class Bob

    def hey(phrase)
        phrase.strip! unless phrase == nil

        if phrase == nil or phrase.empty?
            message = 'Fine. Be that way!'
        elsif phrase.upcase == phrase
            message = 'Woah, chill out!'
        elsif phrase[-1] == '?'
            message = 'Sure.'
        else 
            message = 'Whatever.'
        end

    end
    
end
