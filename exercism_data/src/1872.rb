def compute(strand_1, strand_2)
    strand_1.chars.each_with_index.count do |nucleotide, i| 
      strand_2[i] != nucleotide && !strand_2[i].nil?
    end
  end