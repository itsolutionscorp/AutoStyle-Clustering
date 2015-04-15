def compute dna1, dna2
      minLength = [dna1, dna2].min { |a, b| a.length <=> b.length }.length


      dna1[0...minLength].each_char.zip(dna2.each_char).count { |a, b| a != b }
    end