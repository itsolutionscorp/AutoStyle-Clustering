require 'Prime'
class Prime
	def nth(num)
		raise ArgumentError, 'Cannot be 0' if num == 0
		Prime.first(num).last
	end
end
