class DNA
  
  def initialize(sequence)
    raise ArgumentError if invalid(sequence)
    @strand = sequence
  end

  def to_rna
    @strand.gsub "T", "U"
  end
  
  private
  
  def invalid(sequence)
    sequence.empty? || !sequence.is_a?(String)
  end

end
