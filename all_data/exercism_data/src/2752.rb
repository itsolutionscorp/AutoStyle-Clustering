def compute(strand1, strand2) 
	  strand_length = strand1.length

	   difference = 0
       (0..strand_length-1).each do |i|   # use ruby style each instead of traditional for loop
	        if strand1[i] != strand2[i]	  # strand1[i] is the same as strand1[i,1]
	        	difference += 1 
	        end
	    end
	    
	    difference   # same as return difference
	end