class Series
	attr_accessor :num

	def initialize (num)
		@num = num.chars.map(&:to_i)
	end

	def slices(series)
		raise ArgumentError if series > num.length
		@num.each_cons(series).to_a
	end
	
end
