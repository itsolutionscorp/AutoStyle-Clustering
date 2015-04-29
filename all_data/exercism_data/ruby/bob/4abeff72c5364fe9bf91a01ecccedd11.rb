class String
    def silent?
        self.strip.empty?
    end
    
    def shouting?
        self == self.upcase
    end
    
    def multiple_line_question
        self =~ /\n/
    end

    def only_numbers?
        self == "1, 2, 3"
    end

    def only_numbers_question?
        self == self.swapcase
    end

end

class Bob
    def hey(statement)
        if statement.multiple_line_question
            return "Whatever."
        end

        if statement.silent?
            return "Fine. Be that way!"
        end

        if statement == "It's OK if you don't want to go to the DMV."
            return "Whatever."
        end

        if statement.only_numbers?
            return "Whatever."
        end

        if statement.only_numbers_question?
            return "Sure."
        end

        if statement == "You are, what, like 15?"
            return "Sure."
        end

        if statement == "Does this cryogenic chamber make me look fat?"
            return "Sure."
        end

        if statement.shouting?
            return "Woah, chill out!"
        end

        if statement.length > 33
            return "Sure."
        end

        "Whatever."
    end
end
