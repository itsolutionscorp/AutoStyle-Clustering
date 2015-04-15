class DNA < String
  NUCLEOBASES = ['A', 'C', 'G', 'T', 'U']

  def count(base)
    unless valid_nucleobase?(base)
      raise ArgumentError, 'That is not a valid nucleobase.'
    end
    nucleotide_counts[base]
  end

  def nucleotide_counts
    chars.each_with_object(initial_counts) do |base, counts|
      counts[base] += 1
    end
  end

  private
  def valid_nucleobase?(base)
    NUCLEOBASES.include? base
  end

  def initial_counts
    dna_nucleobases = Hash.new(0)
    (NUCLEOBASES - ['U']).each_with_object(dna_nucleobases) do |base|
      dna_nucleobases[base] = 0
    end
  end
end
