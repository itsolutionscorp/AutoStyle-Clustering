def compute(dna_f, dna_s)
	  index = 0
          dna_f = dna_f[0, dna_s.size-1] unless dna_f.size == dna_s.size
	  dna_f.each_char.select{|c| index += 1; dna_s[index-1] != c}.size
  end