=begin
program that will take a year and report if it is a leap year.

The tricky thing here is that a leap year occurs:
on every year that is evenly divisible by 4
  except every year that is evenly divisible by 100
    unless the year is also evenly divisible by 400
=end

class Year
    def self.leap?(year)
        if (year%4==0 && year%100!=0) then
        	return true
        end
        if (year%100==0 && year%400==0) then
        	return true
        end
    end
end
