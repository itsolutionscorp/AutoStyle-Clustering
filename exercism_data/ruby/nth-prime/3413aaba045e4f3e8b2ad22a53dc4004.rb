require 'prime'
class Prime
	def self.nth(number)
		if number < 1
			raise ArgumentError.new("Number must be >= 1")
		else	
			Prime.first(number)[-1]
		end
	end
end
