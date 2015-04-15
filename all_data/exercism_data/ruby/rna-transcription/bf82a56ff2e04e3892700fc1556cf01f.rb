class Complement
  COMPLEMENTS = {"G" => "C", "C" => "G", "A" => "U", "T" => "A"}

  def self.of_dna(dna)
    dna = dna.chars.map {|char| char = COMPLEMENTS[char]}.join
  end

  def self.of_rna(rna)
    rna = rna.chars.map {|char| char = COMPLEMENTS.key(char)}.join
  end
end
