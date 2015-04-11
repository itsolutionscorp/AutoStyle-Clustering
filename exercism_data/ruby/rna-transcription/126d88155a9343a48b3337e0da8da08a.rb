module Nucleobase
  THYMINE = 'T'
  URACIL = 'U'
end

# A nucleobase sequence supports comparison with Strings
class NucleicAcid
  def initialize(sequence)
    @sequence = sequence.to_str
  end

  def to_str
    @sequence
  end

  def ==(other)
    equal?(other) || other == @sequence
  end
end

# A sequence constructed form the alphabet A, T, G, C.
class DNA < NucleicAcid
  def to_rna
    RNA.new(@sequence.tr(Nucleobase::THYMINE, Nucleobase::URACIL))
  end
end

# A sequence constructed form the alphabet A, U, G, C.
class RNA < NucleicAcid
  def to_dna
    DNA.new(@sequence.tr(Nucleobase::URACIL, Nucleobase::THYMINE))
  end
end
