class DNA < String
  NUCLEOTIDES = %w{ A C T G U}
  DNA_NUCLEOTIDES = %w{ A C T G}

  alias_method :orig_count, :count

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)){|nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    }
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    orig_count(nucleotide)
  end
end
