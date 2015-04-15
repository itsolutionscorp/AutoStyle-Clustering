class DNA
  def initialize(dna_strand)
    @dna_strand = dna_strand
  end

  def hamming_distance(other_dna_strand)
    hamming_distance_calculator(other_dna_strand).calculate_hamming_distance
  end

  private

  def hamming_distance_calculator(other_dna_strand)
    HammingDistanceCalculator.new(dna_strand, other_dna_strand)
  end

  class HammingDistanceCalculator
    def initialize(dna_strand, other_dna_strand)
      @dna_strand, @other_dna_strand = dna_strand, other_dna_strand
    end

    def calculate_hamming_distance
      comparable_indices.count do |index|
        nucleotides_at_index_differ?(index)
      end
    end

    private

    def nucleotides_at_index_differ?(index)
      dna_strand[index] != other_dna_strand[index]
    end

    def comparable_indices
      (0...shorter_strand_length)
    end

    def shorter_strand_length
      [dna_strand.length, other_dna_strand.length].min
    end

    attr_reader :dna_strand, :other_dna_strand
  end

  attr_reader :dna_strand
end
