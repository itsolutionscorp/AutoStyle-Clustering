def compute(sequence1, sequence2)
	
    count = 0;
	
    for i in 0..sequence1.length
	
      if sequence1[i] != sequence2[i]
	    count = count + 1
	  end
    end

    count
  end