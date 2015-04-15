class Sieve
	
	attr_reader :upTo

	def initialize(upTo)
		@upTo = upTo
	end
	
	def primes(arr = Array(2..upTo), n = 0)
		arr = arr.reject { |element| (element % arr[n] == 0 && element != arr[n]) }
		n += 1
		return arr if n == arr.length
		self.primes(arr, n)
	end

end
