class Nucleotide
  def self.from_dna(strand)
    new(strand)
  end

  def initialize(strand)
    raise ArgumentError if invalid?(strand)
    @strand = strand.chars
  end

  def count(target)
    @strand.count { |nucleotide| target == nucleotide }
  end

  def histogram
    sequence = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 } # default sequence
    @strand.each { |nucleotide| sequence[nucleotide] = count(nucleotide) }
    sequence
  end

  private

  def invalid?(strand)
    strand.chars.detect { |nucleotide| !%w(A T C G).include? nucleotide }
  end
end
