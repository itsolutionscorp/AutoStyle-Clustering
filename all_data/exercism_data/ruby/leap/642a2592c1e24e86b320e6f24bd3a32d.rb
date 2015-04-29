class Year

	def self.leap?(year_num)
		if year_num % 4 == 0
			if year_num % 100 == 0
				if year_num % 400 == 0
					return true
				end
				return false
			end
			return true
		end 
		return false
	end 

end
