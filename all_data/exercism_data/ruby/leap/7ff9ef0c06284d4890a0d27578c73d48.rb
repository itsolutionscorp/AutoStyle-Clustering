class Year
	def initialize(year)
		@year = year
	end

	def leap?
		if @year%4 == 0
			if @year%100 ==0
				if @year%400 ==0
				  return true
			    else
				  return false
				end
			else
				return true
			end
	    end
		
 	end
end