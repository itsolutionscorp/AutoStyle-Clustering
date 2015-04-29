class Prime
	def self.nth(n)
		raise ArgumentError if n < 1
		
		upper_bound = n < 6 ? 14 : n*Math.log(n) + n*Math.log(Math.log(n)) + 1

		field = Array.new(upper_bound, true)
		field[0,1] = [false, false]
		primes_found = []
		
		field.each_with_index { |is_prime, i|
			next unless is_prime
			primes_found << i
			break i if primes_found.length == n
			field = self.sieve(field, i)
		}

		primes_found.last
	end

	protected
	def self.sieve(field, n, offset=0)
		max_factor = ((field.length+offset)/n).truncate

		((n+offset)..max_factor).each { |f|
			field[n*f-offset] = false
		}

		field
	end
end
