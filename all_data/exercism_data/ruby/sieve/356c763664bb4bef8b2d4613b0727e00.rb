class Sieve
	def initialize(limit)
		@limit = limit
	end

	def primes
		found = []

		candidates = Array.new(@limit, true)
		candidates[0] = false
		candidates[1] = false

		candidates.each_with_index{ |v, i|
			next if not v
			found << i
			(i*2..@limit).step(i).each{|j| candidates[j] = false }
		}

		found
	end
end
