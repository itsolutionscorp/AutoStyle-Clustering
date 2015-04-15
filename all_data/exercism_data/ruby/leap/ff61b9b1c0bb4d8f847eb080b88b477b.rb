class Year

	def Year.leap? (inputYear)
		inputYear % 400 == 0 || (inputYear % 4 == 0 && inputYear % 100 != 0)
	end


end
