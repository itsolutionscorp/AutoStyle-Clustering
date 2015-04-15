def compute(strand1, strand2)
	if strand1 == strand2
	  return 0
	end
	distance = 0
	i = 0
	len = strand1.length <= strand2.length ? strand1.length : strand2.length
	while i < len
	  if strand1[i] != strand2[i]
	    distance += 1
	  end
	  i += 1
	end
    return distance 
  end