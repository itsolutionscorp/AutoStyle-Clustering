class Year
	attr_reader :year 
	
	def initialize(year)
		@year = year
	end

	def leap?
		if ( (@year % 4 === 0) )
			return true unless ( (@year % 100 === 0) && (@year % 400 != 0) )
		end 

		false
	end

end
