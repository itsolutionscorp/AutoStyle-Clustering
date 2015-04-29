class Series
	def initialize(text)
		@items = text.chars.collect{|c| c.to_i}
	end

	def slices(size)
		raise(ArgumentError) if @items.length < size
		(0..@items.length - size).inject([]){|result, i| result << @items.slice(i, size)}
	end
end
