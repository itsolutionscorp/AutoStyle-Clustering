class Complement
  @dna_to_rna = { 'C' => 'G', 'G' => 'C', 'A' => 'U', 'T' => 'A'}
  @rna_to_dna = @dna_to_rna.invert
  def self.of_dna(base_chain)
    converted = ''
    base_chain.each_char do |c|
      converted += @dna_to_rna[c]
    end
    converted
  end

  def self.of_rna(base_chain)
    converted = ''
    base_chain.each_char do |c|
      converted += @rna_to_dna[c]
    end
    converted
  end
end
