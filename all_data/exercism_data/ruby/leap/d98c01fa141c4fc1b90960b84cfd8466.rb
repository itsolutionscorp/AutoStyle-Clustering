class Year
	def self.leap?(a)
		(a % 400 == 0) || (a % 100 != 0 && a % 4 == 0)
	end
end
