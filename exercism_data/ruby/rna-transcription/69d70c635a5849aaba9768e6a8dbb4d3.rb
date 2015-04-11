class Complement 
  @base_complement = {'A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A'}

  def self.of_dna(dna_sequence)
  	dna_sequence.chars.reduce([]) do |rna_complement, nucleotide|
      rna_complement.push(@base_complement[nucleotide])
    end.join
  end

  def self.of_rna(rna_sequence)    
    rna_sequence.chars.reduce([]) do |dna_complement, nucleotide|
      dna_complement.push(@base_complement.invert[nucleotide])
    end.join    
  end

end
