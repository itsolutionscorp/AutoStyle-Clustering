# Constants for biological molecules
module Nucleotide
  THYMINE = 'T'
  URACIL = 'U'
end

# A nucleotide sequence, comparable with Strings
NucleicAcid = Struct.new(:sequence) do           # rubocop:disable ConstantName
  include Nucleotide

  def to_str
    sequence.to_str
  end

  def to_s
    sequence.to_s
  end

  def ==(other)
    equal?(other) || other == sequence
  end

protected

  def transcribe(from: nil, to: nil)
    sequence.tr(from, to)
  end
end

# A sequence constructed from the alphabet A,T,G,C.
class DNA < NucleicAcid
  def to_rna
    RNA.new(transcribe(from: THYMINE, to: URACIL))
  end
end

# A sequence constructed from the alphabet A,U,G,C.
class RNA < NucleicAcid
  # `def to_dna` when needed
end
