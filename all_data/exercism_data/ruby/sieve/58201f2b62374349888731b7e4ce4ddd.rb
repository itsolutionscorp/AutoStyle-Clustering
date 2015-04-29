class Sieve
	
	def initialize(n)
		@numbers = Array.new(n, true)
	end

	def primes
		if @primeNumbers
			return @primeNumbers
		end

		@primeNumbers = []

		@numbers.each_with_index do |n, i|
			i += 2
			if @numbers[i]
				@primeNumbers.push(i)
				uncheckMultiples(i)
			end
		end

		@primeNumbers
	end

	private 

		def uncheckMultiples(n)
			((n+n)..@numbers.size).step(n) do |i|
				@numbers[i] = false
			end
		end

end
