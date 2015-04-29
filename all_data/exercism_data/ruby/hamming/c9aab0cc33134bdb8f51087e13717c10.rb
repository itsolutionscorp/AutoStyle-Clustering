class Hamming

  def self.compute(dna1, dna2)                  # DNA strands
    hamdist = 0                                 # hamming distance counter
    nuc_length = [dna1.length, dna2.length].min # shortest strand
    i = 0                                       # intialize hamming counter
    while i < nuc_length
      if dna1[i] != dna2[i]
        hamdist += 1                            # hamdist counter
      end
      i += 1
    end
    hamdist                                     # final hamming distance
  end
end
