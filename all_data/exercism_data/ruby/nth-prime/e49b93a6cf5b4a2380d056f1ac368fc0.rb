require 'prime'

class Prime
	def nth(num)
		if num > 0
			(Prime.first num).last
		else
			raise ArgumentError, "No results for #{num}", caller
		end
	end
end
