class Sieve
	attr_reader :primes
	def initialize(n)
		numbers = Array.new(n + 2,true)
		(2..n).each do |potential_prime|
			if numbers[potential_prime] then
				(potential_prime*2..n).step(potential_prime).each do |i|
					numbers[i] = false
				end
			end
		end

		@primes = Array.new
		(2..n).each do |i|
			primes.push(i) if numbers[i]
		end
	end
end
