require 'prime'

class Sieve
	def initialize(num)
		@num = num
	end

	def primes
		array = []
		Prime.each(@num) { |p| array << p }
	end
end
