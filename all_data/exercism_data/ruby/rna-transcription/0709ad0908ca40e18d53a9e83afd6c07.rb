class Complement
  COMPLETENT_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  attr_reader :strand

  class << self
    def of_dna(strand)
      new(strand).to_rna
    end

    def of_rna(strand)
      new(strand).to_dna
    end
  end

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    nucleotides.map { |nucleotide| COMPLETENT_MAP[nucleotide] }.join
  end

  def to_dna
    nucleotides.map { |nucleotide| COMPLETENT_MAP.key(nucleotide) }.join
  end

  private

  def nucleotides
    @_nucleotides ||= strand.chars
  end

end
