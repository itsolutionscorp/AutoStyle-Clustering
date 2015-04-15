class Nucleotide
  def initialize(strand)
    raise ArgumentError unless strand.match(/^[ACTG]*$/)
    @strand = strand
  end

  def self.from_dna(strand)
    new(strand)
  end

  def count(nucleotide)
    @strand.count(nucleotide)
  end

  def histogram
    ['A', 'T', 'C', 'G'].map do |nucleotide|
      [nucleotide, count(nucleotide)]
    end.to_h
  end
end
