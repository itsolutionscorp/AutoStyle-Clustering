class Year
	def self.leap?(year)
		if year % 400 == 0 then
			return true
		elsif (year % 4 == 0) and (year % 100 > 0) then
			return true
		else
			return false
		end
	end
end
