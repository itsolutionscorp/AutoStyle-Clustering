class Series
	def initialize(series_string)
		@series_string = series_string
	end
	def slices(slice_length)
		raise ArgumentError if slice_length > @series_string.length
		slices_result = Array.new
		(0..(@series_string.length-slice_length)).each do |series_index|
			result = Array.new
			(0..slice_length-1).each do |slice_index|
				result.push @series_string[series_index + slice_index].to_i
			end
			slices_result.push result
		end
		slices_result
	end
end
