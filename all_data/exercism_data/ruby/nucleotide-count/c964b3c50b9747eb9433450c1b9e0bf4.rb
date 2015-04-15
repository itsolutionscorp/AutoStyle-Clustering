class Nucleotide
  BASES = %w(A T C G)

  def self.from_dna(dna)
    new(dna.chars)
  end

  def initialize(sequence)
    @sequence = sequence
    raise ArgumentError unless valid?
  end

  def count(base)
    sequence.count(base)
  end

  def histogram
    BASES.each_with_object({}) do |base, counts|
      counts[base] = sequence.count(base)
    end
  end

  private

  attr_reader :sequence

  def valid?
    BASES | sequence == BASES
  end
end
