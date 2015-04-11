class Year

	def self.leap?(year)
		return false if year % 4 != 0 || (year % 100 == 0 && year % 400 != 0) #Phrased this way so that the ~75% of years will be filtered out on the first test.
		return true
	end

end
