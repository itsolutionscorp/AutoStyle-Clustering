class DNA
  NUCLEOTIDE_BASES = %w(G A T C U)
  DEFAULT_BASE = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  def initialize string
    @string = string.to_s.upcase
  end
  
  def count base
    raise ArgumentError unless NUCLEOTIDE_BASES.include? base.to_s.upcase
    nucleotide_counts[base.to_s.upcase] || 0
  end
  
  def nucleotide_counts
    @nucleotide_counts ||= DEFAULT_BASE.merge base_frequency
  end
  
  private
  
  def nucleotides
    @nucleotides ||= @string.split(//)
  end
  
  def base_frequency
    @base_frequency ||= nucleotides.each_with_object(Hash.new 0){|base, counts| counts[base]+= 1}
  end
end
