class Year
	def self.leap?(year)
		if year % 400 == 0 
			return  true
		end	
		if year % 4 ==0 && year % 100 !=0
			return true
		end
	end
end	
	 	
	 
	 
