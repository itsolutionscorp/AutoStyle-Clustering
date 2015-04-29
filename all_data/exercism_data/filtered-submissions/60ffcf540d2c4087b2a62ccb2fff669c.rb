def compute(strand1, strand2)
    upper_bound = [strand1.length-1, strand2.length-1].min
    (0..upper_bound).reduce(0) do |total, index|
      total += 1 if strand1[index] != strand2[index]
      total
    end
  end