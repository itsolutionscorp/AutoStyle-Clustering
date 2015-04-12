def compute(a, b)
    hamming_distance = 0
    
  	if a != b
  	  a.each_char.with_index(0) do |c, i|
  	    if c != b[i]
  	      hamming_distance += 1
  	    end
  	  end
  	end
  	
  	hamming_distance
  end