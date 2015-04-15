class Year
	def self.leap? num
		return true if ((num % 4 == 0 and num % 100 != 0) or num % 400 == 0)
	end
end
