class Hamming

  def self.compute(dna1, dna2)

    length_to_analize = [ dna1.length, dna2.length ].min

    0.upto(length_to_analize - 1).count { |i| dna1[i] != dna2[i] }

  end
end
