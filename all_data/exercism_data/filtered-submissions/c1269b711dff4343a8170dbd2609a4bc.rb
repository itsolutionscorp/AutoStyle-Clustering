def compute dna1, dna2
      if dan1.length > dna2.length
        dna1, dna2 = dna2, dna1
      end

      dna1.each_char.zip(dna2.each_char).select { |a, b| a != b }.size
    end