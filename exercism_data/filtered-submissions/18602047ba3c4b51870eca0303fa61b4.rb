def compute(strand_a, strand_b)
    strand_a.size < strand_b.size ?
      strand_a.chars.zip(strand_b.chars).count {|a, b| a != b} :  
      strand_b.chars.zip(strand_a.chars).count {|a, b| a != b}  
  end