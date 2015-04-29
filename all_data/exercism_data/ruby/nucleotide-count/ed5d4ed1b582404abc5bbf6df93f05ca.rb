class DNA
  attr_reader :strand  
  NUCLEOTIDES = ['A', 'T', 'C', 'G']

  def initialize(strand)
    @strand = strand
  end

  def count(nucleotide) 
    raise ArgumentError, "Invalid nucleotide" unless valid?(nucleotide)
    strand.count(nucleotide)
  end

  def valid?(nucleotide)
    (NUCLEOTIDES + ['U']).include?(nucleotide) 
  end

  def nucleotide_counts
    NUCLEOTIDES.reduce({}) do |summary , nucleotide|
      summary[nucleotide] = count(nucleotide)
      summary
    end
  end
end
