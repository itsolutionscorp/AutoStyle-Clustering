class Year
    def self.leap?(year)
        if (year%4==0)
            if (year%100==0)
                if(year%400==0)
                    return true
                else
                    return false
                end
            else
                return true
            end
        else
            return false
        end
    end
end
