class Year

	def self.leap?(year)
		if year % 4 == 0
			if year % 100 == 0
				return false unless year % 400 == 0
			end
			return true
		end
		return false
	end

end
