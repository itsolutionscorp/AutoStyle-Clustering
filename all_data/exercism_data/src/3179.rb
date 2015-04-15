def compute(strand_1, strand_2)
    strand_1.chars.each_with_index.count do |nucleotide, index| 
      nucleotide != strand_2[index] unless strand_2[index] == nil
    end
  end