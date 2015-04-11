class Series

	def initialize(string)
		@series = string.split(//).map { |element| element.to_i }
	end
	
	def slices(slice_length)
		raise ArgumentError.new('Slices cannot be longer than the original series.') if slice_length > @series.length	
		slices = []
		@series.each_cons(slice_length) {	|slice| slices << slice }
		slices
	end

end
