class Series
	
	def initialize(series)
		@series = series.each_char.map {|c| c.to_i}
	end

	def slices(n)
		raise ArgumentError if n > @series.size
		@series.each_cons(n).map { |p| p }
	end

end
