def compute(strand1, strand2)
    count = 0
    0.upto([strand1.length, strand2.length].min - 1) do |i|
      count += 1 if strand1[i] != strand2[i]
    end
    count
  end