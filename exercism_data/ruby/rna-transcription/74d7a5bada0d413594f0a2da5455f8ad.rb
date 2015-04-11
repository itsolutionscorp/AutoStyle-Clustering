class RibonucleicAcid < String
  def initialize nucleotides
    super nucleotides
  end

end

class DeoxyribonucleicAcid < String
  def initialize nucleotides
    super nucleotides
  end

  def to_rna
    RibonucleicAcid.new self.gsub('T', 'U')
  end
end
