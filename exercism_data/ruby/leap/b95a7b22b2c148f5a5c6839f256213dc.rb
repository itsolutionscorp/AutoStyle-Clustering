class Year
	def self.leap?(year)
	    multiple_of_4 = year % 4 == 0
	    not_multiple_of_100 = year % 100 != 0
	    multiple_of_400 = year % 400 == 0
		multiple_of_4 && (not_multiple_of_100 || multiple_of_400)
	end
end
