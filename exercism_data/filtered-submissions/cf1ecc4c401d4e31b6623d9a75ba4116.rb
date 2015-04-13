def compute(dna1,dna2)
      match = 0
      dna1.each_char.each_with_index do |char, i|
        if dna1[i] != nil && dna2[i] != nil
          match += 1 if dna1[i]!=dna2[i]
        end
      end
      match
    end