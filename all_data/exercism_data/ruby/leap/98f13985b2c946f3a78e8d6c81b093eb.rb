class Year
	class << self
		def leap?(year)
			if year % 400 == 0
				true
			elsif year % 4 == 0
				year % 400 > 0 and year % 100 > 0
			else
				false
			end
		end
	end
end
