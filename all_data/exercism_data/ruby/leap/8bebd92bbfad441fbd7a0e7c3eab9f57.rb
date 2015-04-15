class Year

	def Year.leap?(year)
		( year % 400 == 0 || ( year % 4 == 0 && year % 100 != 0 ) ) ? true : false
	end
end
