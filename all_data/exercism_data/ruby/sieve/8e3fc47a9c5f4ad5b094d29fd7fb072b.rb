class Sieve
	attr_reader :primes
	def initialize(n)
		numbers = Array.new(n + 2,true)
		(2..n).each do |potential_prime|
			if numbers[potential_prime] == true then
				multiple = 2
				while multiple * potential_prime <= n do
					numbers[multiple * potential_prime] = false
					multiple += 1
				end
			end
		end

		@primes = Array.new
		(2..n).each do |i|
			if numbers[i] then
				primes.push(i)
			end
		end
	end
end
