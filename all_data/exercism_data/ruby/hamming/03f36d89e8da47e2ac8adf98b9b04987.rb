class DNA
  def initialize(dna_sequence)
    @dna_sequence=dna_sequence.chars
  end
  def hamming_distance(input_sequence)
    @dna_sequence.zip(input_sequence.chars).count {|a,b| a!=b && b }
  end
end

#DNA.new('GGACTGA').hamming_distance('GGACTGA')
