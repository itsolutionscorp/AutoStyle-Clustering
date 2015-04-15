require 'prime'

class Prime

	def nth (numb)
		raise ArgumentError if numb <= 0 
		(Prime.first numb).last
	end

end
