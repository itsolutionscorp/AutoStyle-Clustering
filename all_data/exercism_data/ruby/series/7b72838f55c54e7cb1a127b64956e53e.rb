class Series
	def initialize(string)
		@atoms = string.chars.map{ |i| i.to_i }
	end
	
	def slices(size)
		raise ArgumentError if size > @atoms.length
		slice_starts(size).to_a.map { |slice| @atoms.slice(slice, size) }
	end
	
private
	def slice_starts(size)
		(0..(@atoms.length - size))
	end
end
