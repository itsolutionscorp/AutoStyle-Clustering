def compute(strand1, strand2)
	  strand_length = strand1.length

	   difference = 0
       (0..strand_length-1).each do |i|
	        if strand1[i] != strand2[i]
	        	difference += 1
	        end
	    end

	    difference
	end