class Bob
        def hey(phrase = '')
                if phrase.strip.empty?
                        'Fine. Be that way!'
                elsif phrase == phrase.upcase
                        'Woah, chill out!'
                elsif phrase[-1..-1] == '?'
                        'Sure.'
                else
                        'Whatever.'
                end
        end
end
