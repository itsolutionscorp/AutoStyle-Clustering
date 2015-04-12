def compute(strand1, strand2)
    distance = 0
    i = 0
    while(strand1[i] && strand2[i]) do
      distance += 1 if strand1[i] != strand2[i]
      i += 1
    end
    distance
  end