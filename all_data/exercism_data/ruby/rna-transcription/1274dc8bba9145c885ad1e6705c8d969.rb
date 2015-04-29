class Complement
  RNA_COMPLEMENTS = {"G" => "C", "C" => "G", "A" => "U", "T" => "A"}

  def self.of_dna(dna)
    dna = dna.chars.map {|char| char = RNA_COMPLEMENTS[char]}.join
  end

  DNA_COMPLEMENTS = {"C" => "G", "G" => "C", "U" => "A", "A" => "T"}

  def self.of_rna(rna)
    rna = rna.chars.map {|char| char = DNA_COMPLEMENTS[char]}.join
  end
end
