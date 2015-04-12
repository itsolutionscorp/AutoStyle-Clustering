class Hamming
	
	def compute(first_strand, second_strand)
		to_compare = first_strand.split('').zip(second_strand.split(''))
		to_compare.select {|pair| pair[0] != pair[1] && pair[1]}.count
	end
end
