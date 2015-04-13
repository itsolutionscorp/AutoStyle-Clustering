def compute(dna1, dna2)
    nuc_length = [dna1.length, dna2.length].min
    hamdist = 0
    nuc_length.times do |i|
      if dna1[i] != dna2[i]
       hamdist += 1
      end
    end
    hamdist
  end