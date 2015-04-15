class Complement
  @dna_complements = { 'A' => 'U', 'G' => 'C', 'C' => 'G', 'T' => 'A'}
  @rna_complements = @dna_complements.invert

  def self.of_dna(dna)
    dna.chars.map {|c| @dna_complements[c]}.join
  end

  def self.of_rna(rna)
    rna.chars.map {|c| @rna_complements[c]}.join
  end
end
