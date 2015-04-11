class Hamming

  def self.compute(dna1, dna2)                  # DNA strands
    nuc_length = [dna1.length, dna2.length].min # shortest strand
    hamdist = 0                                 # intialize hamming counter
    nuc_length.times do |i|
      if dna1[i] != dna2[i]
       hamdist += 1                             # hamdist counter
      end
    end
    hamdist                                     # final hamming distance
  end
end
