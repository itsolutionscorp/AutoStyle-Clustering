class Year
	def self.leap?(year)
		(year%4==0 && year%100!=0)  || year%400==0 ? "Yes, #{year} is a leap year" : "No, #{year} is not a leap year"
	end
end
