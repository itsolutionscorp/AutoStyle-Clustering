class Hamming
  def self.compute(dna_strands_one, dna_strands_two)
    hamming_distance_calculator = HammingDistanceCalculator.new(dna_strands_one, dna_strands_two)
    hamming_distance_calculator.call
  end
end

class HammingDistanceCalculator
  def initialize(dna_strand_one, dna_strand_two)
    @dna_strand_one = dna_strand_one
    @dna_strand_two = dna_strand_two
  end

  def call
    (0...minimum_strand_length).count do |index|
      !nucleotides_equal?(@dna_strand_one[index], @dna_strand_two[index])
    end
  end

  private

  def minimum_strand_length
    [@dna_strand_one.length, @dna_strand_two.length].min
  end

  def nucleotides_equal?(nucleotide_one, nucleotide_two)
    nucleotide_one == nucleotide_two
  end

end
