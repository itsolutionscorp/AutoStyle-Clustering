class Year
	def initialize(is_leap_year)
	 @is_leap_year = is_leap_year
	end
	
	def leap?
		true if divisible_by_four &&
		(not_divisible_by_cent ||
		divisible_by_fourcent)
	end
	
	def divisible_by_four
		@is_leap_year % 4 < 1
	end
	
	def not_divisible_by_cent
		@is_leap_year % 100 > 0
	end
	
	def divisible_by_fourcent
		@is_leap_year % 400 < 1
	end 
end
