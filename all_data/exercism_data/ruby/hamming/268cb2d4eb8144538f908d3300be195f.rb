class Hamming
  def self.compute(dna_strand_1, dna_strand_2)
    hamming_distance_calculator.(dna_strand_1, dna_strand_2)
  end

  def self.hamming_distance_calculator
    HammingDistanceCalculator.new 
  end
end

class HammingDistanceCalculator
  def call(dna_strand_1, dna_strand_2)
    molecule_length = minimum_strand_length dna_strand_1, dna_strand_2
    (0...molecule_length).reduce(0) do |result, index|
      nucleotides_equal?(dna_strand_1[index], dna_strand_2[index]) ? result : result + 1
    end
  end

  private
  def minimum_strand_length(strand_1, strand_2) 
    [strand_1.length, strand_2.length].min
  end

  def nucleotides_equal?(nucleotide_1, nucleotide_2)
    nucleotide_1 == nucleotide_2
  end
end
