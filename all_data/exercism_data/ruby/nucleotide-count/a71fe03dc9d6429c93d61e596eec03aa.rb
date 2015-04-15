class DNA
  BASES = %w(G A T C U)
  def initialize string
    @string = string.to_s.upcase
  end
  def count base
    raise ArgumentError unless BASES.include? base.to_s.upcase
    nucleotide_counts[base.to_s.upcase] || 0
  end
  def nucleotide_counts
    @nucleotide_counts ||= {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}.merge(frequency nucleotides)
  end
  private
  def nucleotides
    @nucleotides ||= @string.split(//)
  end
  def frequency array
    array.each_with_object(Hash.new 0){|base, counts| counts[base]+= 1}
  end
end
