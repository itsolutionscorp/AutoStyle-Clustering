require 'Prime'
class Sieve
	def initialize(arg)
		@arg = arg
		@array = []
	end
	def primes
		Prime.each(@arg) do |a|
			@array << a 
		end
		return @array
	end
end
