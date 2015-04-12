def compute(strand1, strand2)
    count = 0
    strand1.size.times do |pos|
      count += 1 if strand1[pos] != strand2[pos]
    end
    count
  end