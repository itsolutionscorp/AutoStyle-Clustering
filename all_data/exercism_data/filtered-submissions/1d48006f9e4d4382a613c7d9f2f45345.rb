def compute(strand_a, strand_b)
    strand_length = [strand_a.length, strand_b.length].min
    strand_length.times.count do |index|
      strand_a[index] != strand_b[index]
    end
  end