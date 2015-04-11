class Year

	def self.leap?(year)
		is_a_leap_year(year) && !is_a_century(year) || is_a_fourth_century(year)
	end


private
	def self.is_a_leap_year(year)
		year % 4 == 0
	end

	def self.is_a_century(year)
		year % 100 == 0
	end

	def self.is_a_fourth_century(year)
		year % 400 == 0
	end

end
