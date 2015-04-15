class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(another_strand)
    acids_from_another_strand = nucleic_acids(another_strand)

    each_acid_with_index.count do |acid, index|
      another_acid = acids_from_another_strand[index]
      mutation?(acid, another_acid)
    end
  end

  private

  def each_acid_with_index
    nucleic_acids(strand).each_with_index
  end

  def nucleic_acids(strand)
    strand.chars.to_a
  end

  def mutation?(this, that)
    this && that && !(this == that)
  end
end
