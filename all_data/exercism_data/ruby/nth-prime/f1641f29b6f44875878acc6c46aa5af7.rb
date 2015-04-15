require 'prime'

class Prime

	def self.nth num
		raise ArgumentError if num < 1
		prime_numbers = Prime.first(num)
		prime_numbers[-1]
	end

end
