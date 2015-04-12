class Hamming

  # Calculates the number of differences in two
  # 'strands' of dna 
	def Hamming.compute(strand1, strand2)
	    # only compare where the strands have corresponding pairs --
      # which means: compare up to the length of the shortest strand
	    max_index = [strand1.length, strand2.length].min - 1
	    
      # compare the strands at each index up to max_index, accumulating a count of differences
      # using 'sum' -- which is returned by the function
	  	(0..max_index).inject(0) do |sum, index|
        # if the pair elements are the same, 
				strand1[index] == strand2[index] ? sum : sum += 1
	    end
	end
end
