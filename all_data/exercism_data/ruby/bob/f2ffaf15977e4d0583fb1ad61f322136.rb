class Bob
        def hey(input)
                return "Woah, chill out!" if (input == input.upcase) && (input.match(/[A-Z]/))
                return "Sure." if(input[-1, 1] == "?" )
                return "Fine. Be that way!" if(input.match( /\A\s*\Z/))
                return "Whatever."
        end
end
