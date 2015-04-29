class Hamming
	def Hamming.compute(strand1, strand2)
	    # calculate max length to evaluate, ignoring rest
	    max_index = [strand1.length, strand2.length].min - 1
	    # compare each index up to max_index, accumulating differences
	  	(0..max_index).reduce(0) do |sum, index|
				strand1[index] == strand2[index] ? sum : sum += 1
	    end
	end
end
