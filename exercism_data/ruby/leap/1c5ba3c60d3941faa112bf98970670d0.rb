require 'date'

class Year
	def self.leap?(year)
		 if (year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0)
			puts "Yes, #{year} is a leap year"
		else
			puts "No, #{year} is not a leap year"
		end
	end
end
