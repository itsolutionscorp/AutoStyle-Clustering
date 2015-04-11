require 'date'

class Year
    def self.leap?(year)
        div_by_4 = year % 4 == 0
        div_by_100 = year % 100 == 0
        div_by_400 = year % 400 == 0

        # It has to be evenly divisible by 4
        if div_by_4
            if div_by_100
                # if it is evenly div by 4, 100 & 400, it is a leapyear
                return div_by_400
            else
                # if it is divisible by 4 but not 100, it is a leap year
                return true
            end
        end
        false
    end
end
