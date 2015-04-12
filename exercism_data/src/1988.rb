def compute(dna1, dna2)
    strand1 = dna1.chars
    strand2 = dna2.chars
    distance = 0
    count = 0
    while count < strand1.length do
      distance += 1 unless strand1[count] == strand2[count]
      count += 1
    end
    distance
  end