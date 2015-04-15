class Sieve
	def initialize(number)
		array =*(2..number)
		i = 0
		while array.last > array[i]
				element = array[i]
				primes = array.delete_if { |x| x%element == 0 && x != element }
				i +=1
		end
		@primes = array
	end
	def primes
		@primes
	end
end
