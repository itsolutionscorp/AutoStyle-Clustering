class Year
    def self.leap?(year)
        leap = false
        if (((year % 4  == 0) && (year % 100 != 0)) ||
                (year % 400 == 0))
            leap = true
        end
        return leap
    end
end
