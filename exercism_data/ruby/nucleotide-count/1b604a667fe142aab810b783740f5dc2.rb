class DNA
  DNA_NUCLEOTIDES=['A', 'C', 'T', 'G']
  RNA_NUCLEOTIDES=['A', 'C', 'U', 'G']
  ALL_NUCLEOTIDES=(DNA_NUCLEOTIDES + RNA_NUCLEOTIDES).uniq

  def initialize(strand)
    @strand = strand
  end

  def count(nucleotide)
    if (invalid_nucleotide(nucleotide))
      raise ArgumentError
    end

    @strand.count(nucleotide) 
  end

  def nucleotide_counts() 
    counts = {}
    DNA_NUCLEOTIDES.each {|symbol| 
      counts[symbol] = count(symbol)
    }
    counts
  end

  private
  def invalid_nucleotide(nucleotide)
    !ALL_NUCLEOTIDES.include?(nucleotide)
  end
end
