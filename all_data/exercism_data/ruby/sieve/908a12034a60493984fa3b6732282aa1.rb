class Sieve
	attr_reader :primes

	def initialize ( max )
		@primes = (2..max).to_a
		
		for number in 2 .. Math.sqrt(max)
				(number*number).step(max, number) do |to_mark|
					@primes.delete(to_mark)
				end
		end
	end
end
