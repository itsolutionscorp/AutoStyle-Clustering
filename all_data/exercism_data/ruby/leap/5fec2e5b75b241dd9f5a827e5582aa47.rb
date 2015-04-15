class Year
	def self.leap?(year)
		if year% 400 == 0
			return 'Year #{year} is a leap year'
		elsif year%4 ==0 && year%100 != 0
			return 'Year #{year} is a leap year'
		end
	end
end
