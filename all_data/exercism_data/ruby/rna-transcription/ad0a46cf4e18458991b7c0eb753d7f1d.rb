class Complement 
  @base_complement = {'A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A'}

  def self.of_dna(dna_sequence)
  	dna_sequence.chars.map do |nucleotide|
      @base_complement[nucleotide]
    end.join
  end

  def self.of_rna(rna_sequence)    
    rna_sequence.chars.map do |nucleotide|
      @base_complement.invert[nucleotide]
    end.join    
  end

end
