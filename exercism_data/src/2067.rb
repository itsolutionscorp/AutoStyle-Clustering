def compute(strand_a, strand_b)
    total = 0
    length = strand_a.length - 1
    
    (0..length).each do |index|
      total += 1 if strand_a[index] != strand_b[index]
    end

    total
  end