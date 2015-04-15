class Year
	def self.leap?(subYear)
		divByFour = subYear % 4
		divByOneHundred = subYear % 100
		divByFourHundred = subYear % 400
		if (divByFour == 0 && divByFourHundred == 0 && divByOneHundred == 0)
			return true
		elsif (divByFour == 0 && divByOneHundred == 0)
			return false
		elsif (divByFour == 0)
			return true
		else
			return false
		end
	end
end
