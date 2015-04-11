class Year

	def Year.leap?(year)
	
		if year % 400 == 0 || ( year % 4 == 0 && year % 100 != 0 ) then
			true
		else 
			false
		end
	end

end
