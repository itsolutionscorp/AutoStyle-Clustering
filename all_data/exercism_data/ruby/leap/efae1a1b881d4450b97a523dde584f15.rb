require 'date'

class Year

	def self.leap?(year)
		if (year % 4 == 0) && (!(year % 100 == 0) || (year % 400 == 0))
			p "Yes, #{year} is a leap year."
		end
	end
end
