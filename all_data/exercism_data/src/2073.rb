def compute(strand_a, strand_b)
      return -1 if strand_a.length != strand_b.length
      strand_a.chars.zip(strand_b.chars).count do | a, b |
        a != b
      end
    end