def compute(dna_1, dna_2)
    if dna_1.length == dna_2.length
      length = dna_1.length
      distance, counter = 0, 0

      while counter != length
        if dna_1[counter] != dna_2[counter]
          distance += 1
        end
        counter += 1
      end
    end
    distance
  end