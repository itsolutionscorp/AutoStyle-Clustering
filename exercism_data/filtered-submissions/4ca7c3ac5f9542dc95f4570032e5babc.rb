def compute(dna1, dna2)
    hamming_distance = 0

    if dna1.length > dna2.length
      dna_tmp = dna1
      dna1 = dna2
      dna2 = dna_tmp
    end

    dna1.chars.each_index do |i|
      hamming_distance +=1 if dna2[i] != dna1[i]
    end
    hamming_distance
  end