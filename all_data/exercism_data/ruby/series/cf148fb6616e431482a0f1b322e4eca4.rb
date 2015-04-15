class Series

	def initialize(series)
		@series = series
	end

	def slices(slice_length)
		raise ArgumentError.new('Not enough digits') if slice_length > @series.length

		result = Array.new
		finish = @series.length - slice_length
		0.upto(finish) do |i|
			result << @series.slice(i, slice_length).chars.to_a.map(&:to_i)
		end

		return result
	end

end
