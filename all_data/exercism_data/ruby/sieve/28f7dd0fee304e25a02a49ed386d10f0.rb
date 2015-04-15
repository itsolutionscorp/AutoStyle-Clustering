class Sieve
	
	def initialize(n)
		@max = n
	end

	def primes
		[*2..@max].tap do |numbers|
			numbers.each do |current|
				numbers.reject! { |n| n > current && n % current == 0 }
			end
		end
	end

end
