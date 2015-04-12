class Hamming
  def compute(s1, s2)
    i = 0
    h = 0
	  while s1[i] && s2[i]
		if s1[i] != s2[i]
		  h += 1
		end
	    i += 1
	  end
	  return h
  end
end
