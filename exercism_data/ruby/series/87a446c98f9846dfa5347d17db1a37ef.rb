class Series
	def initialize(chain)
		@digits = chain.chars.map { |n| n.to_i }
	end

	def slices(limit)
		raise ArgumentError if limit > @digits.length
		start = 0
		series = []
		until (limit + start) == (@digits.length + 1) do
			series <<  @digits.slice(start...limit+start)
			start += 1
		end
		series
	end
end
