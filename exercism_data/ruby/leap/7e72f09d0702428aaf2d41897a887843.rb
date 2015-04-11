class Year
	def self.leap?(y)
		return true if y%400 == 0 or (y%4 == 0 and y%100 != 0)
		return false 
	end
end
