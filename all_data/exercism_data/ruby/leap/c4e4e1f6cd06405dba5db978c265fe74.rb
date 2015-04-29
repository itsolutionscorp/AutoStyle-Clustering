class Year
    def self.leap? (year)
        if (year % 4 == 0)
            ( year % 100 != 0 ) || ( year % 400 == 0 ) ? true: false
        else
        return false
        end
    end
end
