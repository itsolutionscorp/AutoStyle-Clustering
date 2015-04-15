module Year
	module_function
	def leap?(year)
		divisible_by = lambda {|number| (year % number) == 0}
		
		(divisible_by.call 400) or
           	((divisible_by.call 4) and !(divisible_by.call 100))
	end
end
