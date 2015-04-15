def compute(dna1,dna2)
    hamming = 0
    min_length = [dna1.length, dna2.length].min

    min_length.times do |i|
      hamming += 1 if dna1[i] != dna2[i]
    end

    hamming
  end