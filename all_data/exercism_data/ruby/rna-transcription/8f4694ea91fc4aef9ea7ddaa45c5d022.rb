class Strand
  include Comparable

  attr_reader :sequence
  alias to_str sequence

  def initialize( sequence )
    @sequence = sequence
  end
  
  def <=>( other )
    other.<=>( sequence )
  end
end

module NucleotideSequenceMapping
  DNA = %w[ A C G T ]
  RNA = %w[ A C G U ]

  DNA_TO_RNA_MAP = Hash[ DNA.zip(RNA) ]

  def dna_to_rna( dna )
    dna.gsub( /[#{DNA.join}]/, DNA_TO_RNA_MAP )
  end
end

class RibonucleicAcid < Strand; end

class DeoxyribonucleicAcid < Strand
  include NucleotideSequenceMapping

  def to_rna
    RibonucleicAcid.new( dna_to_rna( sequence ) )
  end
end
