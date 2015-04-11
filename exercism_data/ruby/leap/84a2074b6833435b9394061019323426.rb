module Year
	def Year.leap?(year)
		return true if (year % 4 == 0) unless ((year % 100 == 0) unless (year % 400 == 0))
		return false
	end
end
