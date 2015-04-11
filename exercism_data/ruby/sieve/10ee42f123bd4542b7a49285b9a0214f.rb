class Sieve
	attr_reader :primes

	def initialize(n)
		@primes = []
		setup_primes(n) if (n > 1)
	end

	:private

	def is_prime?(n)
		!primes_within(Math.sqrt(n)).any? { |p| (n % p == 0) }
	end

	def nth_candidate(n)
		n * 2 + 1
	end

	def primes_within(n)
		@primes.select{ |p| p <= n }
	end

	def setup_primes(n)
		@primes << 2
		(1..(n - 1) / 2).each do |i| 
			p = nth_candidate(i)
			@primes << p if is_prime?(p) 
		end
	end
end
