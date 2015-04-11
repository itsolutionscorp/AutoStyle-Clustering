require 'prime'

class Prime
	def nth(number)
		if (number > 0) 
			(Prime.first number).last
		else
			throw ArgumentError
		end
	end
end
