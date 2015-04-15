class Year
	def self.leap?(year)
		result = false
		#on every year that is evenly divisible by 4
		if year % 4 == 0 then result = true end
		#except every year that is evenly divisible by 100
		if year % 100 == 0 then result = false
		  #unless the year is also evenly divisible by 400
			if year % 400 == 0 then result = true end
		end
    result 
	end
end
