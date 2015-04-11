class DNA
  def initialize nucleotide
    @nucleotide = nucleotide
  end

  def to_rna
    @nucleotide.gsub('T', 'U')
  end
end
