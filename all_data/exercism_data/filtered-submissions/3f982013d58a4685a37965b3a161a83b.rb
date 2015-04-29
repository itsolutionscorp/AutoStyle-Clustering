def compute(sequence1, sequence2)
    distance = 0
	shortSeq = (sequence1.size < sequence2.size) ? sequence1 : sequence2
	shortSeq.each_char.with_index do |c, i|
      if sequence1[i] != sequence2[i] then
	    distance += 1
	  end
    end
    distance
  end