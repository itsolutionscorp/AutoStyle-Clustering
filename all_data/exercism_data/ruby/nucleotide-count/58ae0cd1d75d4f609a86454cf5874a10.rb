class NucleicAcid
  module Base
    DNA = %w(A G C T)
    RNA = %w(A G C U)
    ALL = DNA | RNA
  end

  attr :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def count(base)
    validate(base)
    sequence.count(base)
  end

  def nucleotide_counts
    bases.reduce({}) { |hsh, base| hsh.merge(base => count(base)) }
  end

  def bases
    raise NotImplementedError
  end

  private

  def validate(base)
    raise ArgumentError unless Base::ALL.include?(base)
  end
end

class DNA < NucleicAcid
  def bases
    Base::DNA
  end
end
