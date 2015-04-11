class Year
	def initialize(is_leap_year)
	 @year = is_leap_year
	end
	
	def leap?
		divisible_by_four &&
		(not_divisible_by_cent ||
		divisible_by_fourcent)
	end
	
	def divisible_by_four
		@year % 4 == 0
	end
	
	def not_divisible_by_cent
		@year % 100 > 0
	end
	
	def divisible_by_fourcent
		@year % 400 == 0
	end 
end
