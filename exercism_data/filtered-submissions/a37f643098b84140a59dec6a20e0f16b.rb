class Hamming

	def compute(strand1, strand2)
		strand1.split("").zip(strand2.split("")).count{ |x,y| x != y }
	end

end
