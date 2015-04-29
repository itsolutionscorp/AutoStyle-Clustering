def compute( strand_1, strand_2 )
    hamming = 0
    index = 0
    strand_1.each_char do |molecule|
      break unless strand_2[index]
      hamming += 1 if molecule != strand_2[index]
      index += 1
    end
    hamming
	end