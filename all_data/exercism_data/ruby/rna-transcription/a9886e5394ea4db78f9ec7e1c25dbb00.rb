class NucleicAcid

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
    DeoxyribonucleicAcid.new to_str.gsub('U', 'T')
  end

end

class DeoxyribonucleicAcid < NucleicAcid

  def to_rna
    RibonucleicAcid.new to_str.gsub('T', 'U')
  end

end
