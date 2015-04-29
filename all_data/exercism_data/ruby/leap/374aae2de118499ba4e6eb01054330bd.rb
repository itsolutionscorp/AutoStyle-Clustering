class Year
	def self.leap?(number)
		if number % 4 == 0
			true unless number % 100 == 0 && number % 400 != 0
		else
			false
		end
	end
end
