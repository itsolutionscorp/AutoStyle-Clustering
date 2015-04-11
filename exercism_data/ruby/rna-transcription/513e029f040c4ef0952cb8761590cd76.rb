class Complement
  @complements = { 'A' => 'U', 'G' => 'C', 'C' => 'G', 'T' => 'A'}

  def self.of_dna(dna)
    dna.chars.map {|c| @complements[c]}.join
  end

  def self.of_rna(rna)
    rna.chars.map {|c| @complements.invert[c]}.join
  end
end
