class Acid
  def initialize(sequence)
    @sequence = sequence
  end

  def to_str
    @sequence
  end

  def ==(other)
    if String === other
      @sequence == other
    else
      super
    end
  end
end

class RibonucleicAcid < Acid
  def self.from_dna_sequence(sequence)
    new sequence.gsub('T', 'U')
  end
end

class DeoxyribonucleicAcid < Acid
  def to_rna(conversion_factory = RibonucleicAcid)
    conversion_factory.from_dna_sequence(@sequence)
  end
end
