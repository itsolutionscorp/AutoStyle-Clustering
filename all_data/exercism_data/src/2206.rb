def compute(strand_1_s, strand_2_s)
    strand_1 = (strand_1_s || '').chars.to_a
    strand_2 = (strand_2_s || '').chars.to_a

    strand_1.length <= strand_2.length ?
      strand_1.zip(strand_2).count{|a, b| a!= b } :
      strand_2.zip(strand_1).count{|a, b| a!= b }
  end