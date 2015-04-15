class Year
	def initialize(y)
		@year = y 
	end

	def leap?
		leapyear = case 
		when @year % 400 == 0
			true
		when @year % 100 == 0
			false
		when @year % 4   == 0
			true 
		else
			false 
		end 
	end
end
