class DNA
  NUCLEOTIDES = %w[A C G T]
  def initialize strand
    @strand = strand
  end

  def to_rna
    @strand.tr DNA::NUCLEOTIDES.join(''), RNA::NUCLEOTIDES.join('')
  end
end

class RNA
  NUCLEOTIDES = %w[A C G U]
end
