class Hamming
  def initialize(strand)
    @strand = strand
  end

  def distance_to(current_strand)
    @strand.chars.map.with_index do |original_base, point|
      is_mutated?(original_base, current_strand[point])
    end.reduce(:+)
  end

  private
  def is_mutated?(original, actual)
    if original == actual
      0
    else
      1
    end
  end


  def self.compute(original_strand, current_strand)
    new(original_strand).distance_to(current_strand)
  end
end
