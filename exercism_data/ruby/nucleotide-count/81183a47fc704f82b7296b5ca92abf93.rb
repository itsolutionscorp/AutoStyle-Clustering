class NucleicAcid
  BASES = %w(A T C G U)

  attr :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def count(symbol)
    raise ArgumentError unless BASES.include?(symbol)
    sequence.count(symbol)
  end
end

class DNA < NucleicAcid
  BASES = %w(A T C G)

  def nucleotide_counts
    BASES.reduce({}) { |hsh, sym| hsh.merge(sym => count(sym)) }
  end
end
