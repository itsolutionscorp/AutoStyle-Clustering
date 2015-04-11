class Year
	def self.leap?(num)
		if num % 4 == 0 && num % 100 != 0 
			true
		elsif num % 400 == 0 
			true
		else
			false
		end
	end
end
