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
    zip_nucleotides
    crop_to_fit
  end

  private

  attr_reader :left, :right

  def zip_nucleotides
    @zipped ||= left.nucleotides.zip(right.nucleotides)
  end

  def crop_to_fit
    @zipped.take(right.length)
  end
end
