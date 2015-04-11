class Hamming
  def initialize(strand)
    @strand = strand
  end

  def distance_to(current_strand)
    [@strand, current_strand].transpose.count do |points|
      mutated?(points.first, points.last)
    end
  end

  private
  def mutated?(original, actual)
    original != actual
  end

  def self.compute(original_strand_string = "", current_strand_string = "")
    original_strand = original_strand_string.chars
    current_strand  = current_strand_string.chars
    new(original_strand).distance_to(current_strand)
  end
end
