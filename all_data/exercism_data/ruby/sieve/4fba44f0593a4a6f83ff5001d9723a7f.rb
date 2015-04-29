require 'prime'
class Sieve
	def initialize num
		@num = num
	end
	def primes
		Prime.take_while {|i| i < @num}
	end
end
