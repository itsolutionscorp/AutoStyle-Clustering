class Hamming
	def compute(strand1, strand2) 
	  strand_length = strand1.length
	  
	   difference = 0
       for i in 0..strand_length-1 
	        if strand1[i,1] != strand2[i,1]	
	        	difference += 1 
	        end
	    end
	    # total number of differences between the strands
	    return difference
	end
end
