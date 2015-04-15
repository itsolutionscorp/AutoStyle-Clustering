class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other)
    pair_up_with(other).count { |left, right| left != right }
  end

  def nucleotides
    strand.chars
  end

  def length
    strand.length
  end

  def take(count)
    nucleotides.take(count)
  end

  private

  attr_reader :strand

  def pair_up_with(other)
    HomologousDNAPair.new(self, DNA.new(other)).nucleotide_pairs
  end
end

class HomologousDNAPair
  def initialize(left, right)
    @left = left
    @right = right
  end

  def nucleotide_pairs
    truncated_left_nucleotides.zip(truncated_right_nucleotides)
  end

  private

  attr_reader :left, :right

  def truncated_left_nucleotides
    left.take(right.length)
  end

  def truncated_right_nucleotides
    right.take(truncated_left_nucleotides.length)
  end
end
