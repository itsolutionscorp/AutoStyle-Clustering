class Year
	def self.leap? year
		year % 400 == 0 || year % 100 > 1 && year % 4 == 0
	end
end
