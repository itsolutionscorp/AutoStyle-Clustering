class Sieve

	def initialize(limit)
		@limit = limit
	end

	def primes
		primes = (2..@limit).to_a
		primes.reduce(primes){ |res, i| res.select { |e| e == i || e%i != 0 }  }
	end

end
