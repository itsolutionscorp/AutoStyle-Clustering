class Complement
  @dna_to_rna = { 'C' => 'G', 'G' => 'C', 'A' => 'U', 'T' => 'A'}
  @rna_to_dna = @dna_to_rna.invert
  def self.of_dna(base_chain)
    base_chain.split('').map { |c| c =  @dna_to_rna[c] }.join
  end

  def self.of_rna(base_chain)
    base_chain.split('').map { |c| c =  @rna_to_dna[c] }.join
  end
end
