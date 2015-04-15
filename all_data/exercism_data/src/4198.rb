def compute(strand_x, strand_y)
    hamming_distance = 0
    shorter_strand_length = [strand_x.length, strand_y.length].min
	
    shorter_strand_length.times do |index|
      hamming_distance += 1 unless strand_x[index].eql?(strand_y[index])
    end
    
    hamming_distance
  end