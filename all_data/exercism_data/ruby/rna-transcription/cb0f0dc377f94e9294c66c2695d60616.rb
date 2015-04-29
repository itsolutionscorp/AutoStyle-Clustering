class DNA
  attr_reader :dna
  def initialize(dna)
    @dna = dna.to_s
    @rna = nil
  end
  
  def to_rna
    @rna ||= @dna.gsub(/t/i, 'u').upcase
  end
end
