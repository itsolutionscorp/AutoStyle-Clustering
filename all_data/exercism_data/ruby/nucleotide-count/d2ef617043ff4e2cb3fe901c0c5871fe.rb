class DNA < String
  NUCLEOBASES = ['A', 'C', 'G', 'T', 'U']

  def count(base)
    raise ArgumentError, 'That is not a valid nucleobase.' unless valid_nucleobase?(base)
    nucleotide_counts[base].to_i
  end

  def nucleotide_counts
    initial_counts = nucleobases
    chars.each_with_object(initial_counts) { |base, counts| counts[base] += 1 }
  end

  private
  def valid_nucleobase?(base)
    NUCLEOBASES.include? base
  end

  def nucleobases
    dna_nucleobases = NUCLEOBASES.reject { |base| base == 'U' }
    Hash[*(dna_nucleobases.map { |base| [base, 0] }).flatten]
  end
end
