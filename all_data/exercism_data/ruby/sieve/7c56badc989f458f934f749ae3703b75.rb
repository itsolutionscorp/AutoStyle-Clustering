class Sieve
	def initialize(n)
		@series=*(2..n)
	end

	def primes
		(2..@series.last).each do |n|
			@series = @series - (n+1..@series.last).select{|i| i % n == 0}
		end
		@series
	end
end
