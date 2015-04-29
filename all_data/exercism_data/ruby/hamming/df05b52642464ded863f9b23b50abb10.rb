class Hamming

  attr_accessor :original_strand, :comparison_strand

  def self.compute(original_strand, comparison_strand)
    new(original_strand, comparison_strand).compute_distance
  end

  def initialize(original_strand, comparison_strand)
    @original_strand, @comparison_strand = original_strand, comparison_strand
  end

  def compute_distance
    acid_pairs.find_all { |acids| acids_mutated?(*acids) }.length
  end

  private

  def acid_pairs
    original_acids.zip(comparison_acids)
  end

  def acids_mutated?(original_acid, comparison_acid)
    original_acid != comparison_acid
  end

  def original_acids
    strand_to_acids(original_strand, minimum_length)
  end

  def comparison_acids
    strand_to_acids(comparison_strand, minimum_length)
  end

  def minimum_length
    @minimum_length ||= [original_strand.length, comparison_strand.length].min
  end

  def strand_to_acids(strand, length)
    strand.split(//)[0..length-1]
  end

end
