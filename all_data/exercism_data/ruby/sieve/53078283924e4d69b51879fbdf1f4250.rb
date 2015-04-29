class Sieve
	def initialize(number)
		@number = number
	end
	def primes
		array =*(2..@number)
		i = 0
		while array.last > array[i]
			primes = array.delete_if{ |x| x%array[i] == 0 && x != array[i] }
			i += 1
		end
		primes 
	end
end
