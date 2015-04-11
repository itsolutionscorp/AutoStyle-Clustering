require 'prime'

class Prime

	def self.nth(n)
		if n == 0
			raise ArgumentError
		else
			Prime.take(n).last
		end
	end

end
