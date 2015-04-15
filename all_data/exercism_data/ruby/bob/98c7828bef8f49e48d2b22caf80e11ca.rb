class Bob

    def initialize
    end

    def hey(phrase)
        message = 'Whatever.'

        if phrase == nil or phrase.empty?
            message = 'Fine. Be that way!'
        else
            blank = true
            phrase.each_char {|c| if c != ' ' then blank = false end}

            if blank
                message = 'Fine. Be that way!'
            elsif phrase.upcase == phrase
                message = 'Woah, chill out!'
            elsif phrase[-1] == '?'
                message = 'Sure.'
            end

        end

        return message

    end
end
