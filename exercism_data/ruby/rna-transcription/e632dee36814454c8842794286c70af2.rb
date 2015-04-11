class Complement
  def self.of_dna(strand)
    new(strand).to_rna
  end

  def self.of_rna(strand)
    new(strand).to_dna
  end

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    with_individual_nucleotides { |nuc| complement[nuc] }
  end

  def to_dna
    with_individual_nucleotides { |nuc| inverse_complement[nuc] }
  end

  private
  attr_accessor :strand

  def with_individual_nucleotides(&block)
    strand.chars.map(&block).join
  end

  def complement
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def inverse_complement
    complement.invert
  end
end
