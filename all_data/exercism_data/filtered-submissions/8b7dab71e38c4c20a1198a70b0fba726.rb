def compute(strand1, strand2)
    count = 0
    i = 0
    strand1.length.times do
      count += 1  if strand1[i] != strand2[i]
      i+=1
    end
    count
  end