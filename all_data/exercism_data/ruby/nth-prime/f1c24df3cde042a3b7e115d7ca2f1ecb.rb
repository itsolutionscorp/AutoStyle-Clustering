require 'prime'
class Prime
	def self.nth(n)

		if n < 1
			raise ArgumentError, "eww wwe"
		else
			Prime.first(n).last
		end
	end

end
