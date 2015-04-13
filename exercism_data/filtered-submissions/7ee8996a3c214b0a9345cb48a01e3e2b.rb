def compute(strand1, strand2)
	  strand_length = strand1.length

	   difference = 0
       for i in 0..strand_length-1
	        if strand1[i] != strand2[i]
	        	difference += 1
	        end
	    end

	    difference
	end