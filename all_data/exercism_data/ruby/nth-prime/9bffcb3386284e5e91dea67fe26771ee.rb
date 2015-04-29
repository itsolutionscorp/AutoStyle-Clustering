class Prime
	# don't feel like calculating primes because there's a library for it
	require 'prime'

	def self.nth(number)
		# throw an error if you don't get a positive integer for input
		raise ArgumentError if number <= 0
		# calculate N primes in an array
		primes = Prime.take(number)
		# return the Nth element
		primes[number - 1]
	end

end
