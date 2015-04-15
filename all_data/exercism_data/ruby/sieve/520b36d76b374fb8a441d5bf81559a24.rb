class Sieve
	attr_reader :num
	def initialize(num)
		@num = num		
	end
	def primes
		i = 2
		prime_hash = {}
		(1..num).map {|i| prime_hash[i] = "X"}
		while i <= num
			prime_hash[i] = "P"
			multiplier = 2
			while multiplier*i <= num
				prime_hash[multiplier*i] = "N"
				multiplier+=1
			end
			i+=1
			while prime_hash[i] != "X" and i<=num
				i+=1				
			end
		end
		prime_hash.select {|k,v| v=="P"}.keys
	end
end
puts Sieve.new(1000).primes
