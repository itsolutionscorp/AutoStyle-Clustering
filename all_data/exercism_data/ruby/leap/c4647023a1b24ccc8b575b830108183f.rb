class Year
	def self.leap?(year)
		false
		if year % 400 == 0
			true
		else 
			if year % 100 == 0 
				false
			else
				true if year % 4 == 0
			end
		end
	end
end
