def compute(strand1, strand2)
      comparison_length = [strand1.length, strand2.length].min - 1
      (0..comparison_length).inject(0){|sum, index| sum + (strand1[index] == strand2[index] ? 0 : 1) }
    end