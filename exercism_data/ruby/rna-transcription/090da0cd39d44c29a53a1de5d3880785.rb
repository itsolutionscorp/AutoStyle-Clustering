class NucleicAcid

  NUCLEOTIDES = {adenine: 'A', cytosine: 'C', guanine: 'G', thymine: 'T', uracil: 'U'}

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence.to_s.upcase
  end

  def to_str
    sequence
  end

  def == string
    to_str == string
  end

  private
  def transpose old_nuc, new_nuc
    to_str.gsub NUCLEOTIDES[old_nuc], NUCLEOTIDES[new_nuc]
  end

end

class RibonucleicAcid < NucleicAcid

  def to_dna
    DeoxyribonucleicAcid.new transpose(:uracil, :thymine)
  end

end

class DeoxyribonucleicAcid < NucleicAcid

  def to_rna
    RibonucleicAcid.new transpose(:thymine, :uracil)
  end

end
