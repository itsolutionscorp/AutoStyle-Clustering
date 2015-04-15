require 'prime'

class Prime
	class << self
		def nth(nth)
			raise ArgumentError if nth < 1
			
			Prime.first(nth).last
		end
	end
end
