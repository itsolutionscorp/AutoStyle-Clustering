require 'prime'

class Prime
	def self.nth(num)
		if (num > 0)
			array = Prime.first num
			return array[num-1]
		else
			raise ArgumentError, "Number must be greater than 0"
		end
	end
end
