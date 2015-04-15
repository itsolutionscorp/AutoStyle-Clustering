def compute(strand_a, strand_b)
    count = 0
    i = 0
    short_strand = [strand_a.length, strand_b.length].min
    while i < short_strand
      count += 1 if strand_a[i] != strand_b[i]
      i += 1
    end
    count
  end