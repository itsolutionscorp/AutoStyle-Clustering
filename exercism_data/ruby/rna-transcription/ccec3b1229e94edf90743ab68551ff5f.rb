class Strand

  attr_reader :sequence

  def initialize( sequence )
    @sequence = sequence
  end

  def respond_to?( sym )
    return true if sym == :to_str
    super( sym )
  end

  def ==( other )
    other == sequence
  end

end

module NucleotideSequenceMapping
  DNA = "ACGT"
  RNA = "ACGU"

  def dna_to_rna( dna )
    dna.tr( DNA, RNA )
  end
end

class RibonucleicAcid < Strand; end

class DeoxyribonucleicAcid < Strand
  include NucleotideSequenceMapping

  def to_rna
    RibonucleicAcid.new( dna_to_rna( sequence ) )
  end
end
