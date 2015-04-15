class Complement 
  @base_dna_complement = {'A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A'}
  @base_rna_complement = @base_dna_complement.invert
  
  def self.of_dna(dna_sequence)
  	dna_sequence.chars.map { |nucleotide|
      @base_dna_complement[nucleotide]
    }.join
  end

  def self.of_rna(rna_sequence)    
    rna_sequence.chars.map { |nucleotide|
      @base_rna_complement[nucleotide]
    }.join    
  end

end
