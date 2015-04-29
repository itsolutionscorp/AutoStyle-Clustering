class DNA
  VALID_NUCLEOTIDE_BASES = %w(G A T C U)
  
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
    @dna_string.count base.to_s.upcase
  end
  
  def is_a_base? base
    VALID_NUCLEOTIDE_BASES.include? base.to_s.upcase
  end
  
  def nucleotide_frequency
    nucleotide_count = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @dna_string.chars.each_with_object(nucleotide_count) do |base, counts| 
      counts[base] = counts[base].to_i + 1
    end
  end
end
