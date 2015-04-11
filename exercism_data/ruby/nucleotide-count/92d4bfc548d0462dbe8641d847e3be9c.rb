class DNA
  NUCLEOTIDE_BASES = %w(G A T C U)
  DEFAULT_BASE = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  def initialize string
    @string = string.to_s.upcase
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
    nucleotide_counts[base.to_s.upcase] || 0
  end
  
  def is_a_base? base
    NUCLEOTIDE_BASES.include? base.to_s.upcase
  end
  
  def nucleotide_frequency
    @string.chars.each_with_object(Hash.new(0).merge(DEFAULT_BASE)) do |base, counts| 
      counts[base]+= 1
    end
  end
end
