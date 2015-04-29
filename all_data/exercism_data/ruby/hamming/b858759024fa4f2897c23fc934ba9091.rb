class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(another_strand)
    acid_pairs(another_strand).count do |acid, another_acid|
      mutation?(acid, another_acid)
    end
  end

  private

  def acid_pairs(another_strand)
    nucleic_acids(strand).zip(nucleic_acids(another_strand))
  end

  def each_acid_with_index
    nucleic_acids(strand).each_with_index
  end

  def mutation?(acid, another_acid)
    another_acid && !(acid == another_acid)
  end

  def nucleic_acids(strand)
    strand.chars
  end
end
