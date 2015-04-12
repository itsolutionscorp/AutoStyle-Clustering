def compute(dna1, dna2)
      distance = 0
      (0...dna1.length).each do |i| 
        distance += 1 unless dna1[i].nil? || dna2[i].nil? || dna1[i] == dna2[i]
      end
      distance
    end