class Series
	def initialize(x)
		@x = x
	end

	def slices(y)
		raise ArgumentError if y > @x.length 
	
		a = @x.chars.to_a.each_with_object([]) { |x, a| a << x.to_i }
		(1..(a.size-y+1)).each_with_object([]) { |z, b| b << a[(z-1)..((y-1)+(z-1))] }
	end
end
