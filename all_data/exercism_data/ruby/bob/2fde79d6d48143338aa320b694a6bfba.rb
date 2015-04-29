class Bob
    def hey(saywhat)
            if saywhat.strip.empty?
                return "Fine. Be that way!"
            elsif saywhat == saywhat.upcase()
                return "Woah, chill out!"
            elsif saywhat.end_with?('?')
                return "Sure."
            else
                return "Whatever."
        end
    end
end
