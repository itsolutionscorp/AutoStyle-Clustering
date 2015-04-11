class Hamming

  def self.compute(dna_1, dna_2)
    differences(dna_1, dna_2).length
  end

  def self.differences(dna_1, dna_2)
    comparison = dna_1.chars.zip(dna_2.chars)
    comparison.keep_if { |chars| (chars.first != chars.last) && chars.last }
  end

end
