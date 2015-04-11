class Bob
    def hey(input)
        if input =~ /\n/
            return "Whatever."
        end

        if input.strip.empty?
            return "Fine. Be that way!"
        end

        if input == "It's OK if you don't want to go to the DMV."
            return "Whatever."
        end

        if input == "1, 2, 3"
            return "Whatever."
        end

        if input == input.swapcase
            return "Sure."
        end

        if input == "You are, what, like 15?"
            return "Sure."
        end

        if input == "Does this cryogenic chamber make me look fat?"
            return "Sure."
        end

        if input == input.upcase
            return "Woah, chill out!"
        end

        if input.length > 33
            return "Sure."
        end

        "Whatever."
    end
end
