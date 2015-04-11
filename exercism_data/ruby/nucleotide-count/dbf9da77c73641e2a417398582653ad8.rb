class DNA
  NUCLEOTIDES = %w{ A C T G U}
  DNA_NUCLEOTIDES = %w{ A C T G}

  def initialize(chain)
    @chain = chain
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)){|nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    }
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    @chain.count(nucleotide)
  end
end
