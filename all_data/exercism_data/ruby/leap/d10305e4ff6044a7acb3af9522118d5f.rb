class Year

	def self.leap?(an)
		if (an%4 == 0 && an%100 != 0) || (an%4 == 0 && an%100 == 0 && an%400 == 0) 
			return "Yes, #{an} is a leap year"
		else
			puts "No, #{an} is not a leap year"
		end
	end
end 
