def compute(strand_one, strand_two)
    d, i = 0, 0

    strand_one.chars do |s1|
      if s1 != strand_two[i]
        d += 1
      end
        i += 1
    end
    d
  end