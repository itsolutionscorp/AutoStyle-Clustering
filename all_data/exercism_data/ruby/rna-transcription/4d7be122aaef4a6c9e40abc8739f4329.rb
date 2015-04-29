class Complement
  attr_accessor :dna_to_rna 
  attr_accessor :rna_to_dna 
  @dna_to_rna = { 'A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A' }  
  @rna_to_dna = { 'A' => 'T', 'C' => 'G', 'G' => 'C', 'U' => 'A' }  
  def self.of_dna(strand)
    strand.chars.map { |c| @dna_to_rna[c] }.join
  end
  def self.of_rna(strand)
    strand.chars.map { |c| @rna_to_dna[c] }.join
  end
end
