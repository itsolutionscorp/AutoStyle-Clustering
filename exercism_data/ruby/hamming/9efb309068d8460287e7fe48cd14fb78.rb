class Hamming
  def self.compute(dna_strand1, dna_strand2)
		dna_strand1.chars.zip(dna_strand2.chars).count { |n1, n2| n1 && n2 && n1 != n2 }
  end
end
