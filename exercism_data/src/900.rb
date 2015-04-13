def compute(dna1, dna2)
    count = 0
    b = 0
    dna1.each_char do |a|
      if a != dna2[b] && dna2[b] != nil
        count += 1
      end
      b += 1
    end
  count
  end