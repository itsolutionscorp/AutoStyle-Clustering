class DNA
  VALID_NUCLEOTIDE_BASES = %w(G A T C U)
  DEFAULT_NUCLEOTIDE_COUNT = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  
  def initialize dna_string
    @dna_string = dna_string.to_s.upcase
  end
  
  def count base
    raise ArgumentError unless is_a_base? base
    get_nucleotide_count_for base
  end
  
  def nucleotide_counts
    @nucleotide_counts ||= nucleotide_frequency
  end
  
  private
  
  def get_nucleotide_count_for base
    nucleotide_counts[base.to_s.upcase]
  end
  
  def is_a_base? base
    VALID_NUCLEOTIDE_BASES.include? base.to_s.upcase
  end
  
  def initial_nucleotide_frequency
    Hash.new(0).merge(DEFAULT_NUCLEOTIDE_COUNT)
  end
  
  def nucleotide_frequency
    @dna_string.chars.each_with_object(initial_nucleotide_frequency) do |base, counts| 
      counts[base]+= 1
    end
  end
end
