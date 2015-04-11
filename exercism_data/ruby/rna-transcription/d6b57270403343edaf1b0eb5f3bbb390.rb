class NucleicAcid

  NUCLEOTIDES = {adenine: 'A', cytosine: 'C', guanine: 'G', thymine: 'T', uracil: 'U'}

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence.to_s.upcase
  end

  def to_str
    "#{sequence}"
  end

  def == string
    to_str == string
  end

end

class RibonucleicAcid < NucleicAcid

  def to_dna
    DeoxyribonucleicAcid.new to_str.gsub(NUCLEOTIDES[:uracil], NUCLEOTIDES[:thymine])
  end

end

class DeoxyribonucleicAcid < NucleicAcid

  def to_rna
    RibonucleicAcid.new to_str.gsub(NUCLEOTIDES[:thymine], NUCLEOTIDES[:uracil])
  end

end
