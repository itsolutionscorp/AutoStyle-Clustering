class DNA

  DNA_NUCLEOTIDES = %w(A T C G)
  RNA_NUCLEOTIDES = %w(A C G U)

  def initialize(dna)
    @dna = dna
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide, memo|
      memo[nucleotide] = count(nucleotide)
    end
  end

  def count(nucleotide)
    if valid_nucleotide?(nucleotide)
      split_dna.count(nucleotide)
    else
      raise ArgumentError
    end
  end
  

  private

  def split_dna
    @dna.split(//)
  end

  def valid_nucleotide?(nucleotide)
    (DNA_NUCLEOTIDES + RNA_NUCLEOTIDES).include?(nucleotide) ||
      nucleotide.to_s.empty?
  end

end
