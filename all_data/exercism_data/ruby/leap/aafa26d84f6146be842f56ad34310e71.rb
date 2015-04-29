class Year
	def self.leap?(x)
		if (x % 4 == 0 && x % 100 != 0) || (x % 400 == 0); true
		end
	end
end
