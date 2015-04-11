class Year

	def self.leap?(y)
		if y % 400 == 0 then return true end
		if y % 100 == 0 then return false end
		if y % 4 == 0 then return true end
		return false
	end
end
