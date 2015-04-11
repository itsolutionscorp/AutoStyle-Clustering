class Series
	def initialize(chain)
		@chain = chain
	end

	def slices(limit)
		raise ArgumentError if limit > @chain.length
		digits = @chain.split('').map! { |n| n.to_i }
		start = 0
		series = []
		until (limit + start) == (digits.length + 1) do
			series <<  digits.slice(start...limit+start)
			start += 1
		end
		series
	end
end
