class Bob
    def hey(input)
        
        # If input is missing or once all whitespace has been stripped is still
        # empty then respond with 'Fine. Be that way!'
        if input == nil or input.strip.empty?
            return 'Fine. Be that way!'
        end
        
        # if input has letters (i.e. not just characters or numbers) and is the
        # same as itself when uppercase then respond with 'Woah, chill out!'
        if (input.match /[A-Za-z]/ and input == input.upcase )
            return 'Woah, chill out!'
        end
        
        # if the last character of input is a question mark then respond with
        # 'Sure.'
        if input[-1,1] == '?'
            return 'Sure.'
        end
        
        # if none of the above are satisfied then just respond with 'Whatever.'
        return 'Whatever.'
        
    end
end
