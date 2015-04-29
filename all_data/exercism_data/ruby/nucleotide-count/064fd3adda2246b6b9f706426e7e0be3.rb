class DNA < String
  NUCLEOBASES = ['A', 'C', 'G', 'T', 'U']

  def count(base)
    unless valid_nucleobase?(base)
      raise ArgumentError, 'That is not a valid nucleobase.'
    end
    chars.count(base)
  end

  def nucleotide_counts
    (NUCLEOBASES - ['U']).each_with_object({}) do |base, counts|
      counts[base] = count(base)
    end
  end

  private
  def valid_nucleobase?(base)
    NUCLEOBASES.include? base
  end
end
