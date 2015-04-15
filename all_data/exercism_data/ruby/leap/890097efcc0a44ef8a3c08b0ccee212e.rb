class Year

	# on every year that is evenly divisible by 4
	# except every year that is evenly divisible by 100
	# unless the year is also evenly divisible by 400
	def self.leap?(leap_year)
		leap_year % 4 == 0 ? 
			leap_year % 100 == 0 ? leap_year % 400 == 0  : true :
			false
	end

end
