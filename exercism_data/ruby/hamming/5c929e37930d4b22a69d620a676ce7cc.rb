class DNA
  def initialize(nucleotides)
    @nucleotides = nucleotides.to_s
  end

  def hamming_distance(dna_string)
    differences = 0
    comparable_sequence_length = [@nucleotides.length, dna_string.length].min
    (0...comparable_sequence_length).each { |i|
      differences += 1 unless @nucleotides[i] == dna_string[i]
    }
    differences
  end
end
