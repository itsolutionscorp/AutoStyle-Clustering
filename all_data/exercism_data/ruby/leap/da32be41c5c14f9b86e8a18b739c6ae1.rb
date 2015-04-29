class Year

    def self.leap?(year)
        if year >= 1000 and year % 100 == 0
            return year % 400 == 0
        else
            return year % 4 == 0
        end
    end

end
