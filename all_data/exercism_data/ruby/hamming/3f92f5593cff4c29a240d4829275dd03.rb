class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(another_strand)
    acid_pairs(another_strand).count do |acid, another_acid|
      mutation?(acid, another_acid)
    end
  end

  private

  attr_reader :strand

  def acid_pairs(another_strand)
    nucleic_acids(strand).zip(nucleic_acids(another_strand))
  end

  def mutation?(acid, another_acid)
    another_acid && acid != another_acid
  end

  def nucleic_acids(strand)
    strand.chars
  end
end
