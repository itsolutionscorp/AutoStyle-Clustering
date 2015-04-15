class Year
	def initialize(number)
		@number = number
	end

	def leap?
		if @number % 4 == 0
			if @number % 400 == 0 and @number % 100 == 0
				return true
			elsif @number % 400 != 0 and @number % 100 != 0
				return true
			end
		else
			return false
		end
	end
end
