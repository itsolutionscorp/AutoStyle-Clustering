class RibonucleicAcid < String

end

class DeoxyribonucleicAcid < String
  def initialize nucleotides
    super nucleotides
  end

  def to_rna
    RibonucleicAcid.new gsub('T', 'U')
  end
end
