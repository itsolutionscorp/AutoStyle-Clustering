def compute(dna1, dna2)
      hamming_distance = 0
      for i in 0...[dna1.length,dna2.length].min
        if dna1[i] != dna2[i]
          hamming_distance += 1
        end
      end
      hamming_distance
    end