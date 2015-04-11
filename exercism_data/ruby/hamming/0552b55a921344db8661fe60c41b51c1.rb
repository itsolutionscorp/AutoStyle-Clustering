class Hamming
  def self.compute strand_a, strand_b
    new(strand_a, strand_b).compute
  end

  def initialize strand_a, strand_b
    @strand_a = strand_a
    @strand_b = strand_b
  end

  def compute
    count_mismatched_strand_pairs
  end

  private

  attr_reader :strand_a, :strand_b

  def count_mismatched_strand_pairs
    strand_pairs.count { |a, b| a != b }
  end

  def strand_pairs
    strand_a
      .chars
      .zip(strand_b.chars)
      .reject{ |a, b| b.nil? }
  end
end
