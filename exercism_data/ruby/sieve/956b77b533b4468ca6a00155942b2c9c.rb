class Sieve
	def initialize(num)
		@limit = num
	end

	def primes
		prime_array = [nil, nil, *2..@limit]
		2.upto(@limit) do |i|
			(i+1).upto(@limit) { |j| prime_array[j] = nil if j % i == 0 } if prime_array[i]
		end
		prime_array.select {|value| value }
	end
end
