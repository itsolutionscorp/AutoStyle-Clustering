class Complement
  COMPLEMENTS = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "T",
    "U" => "A"
  }

  def self.of_dna(dna)
    dna.split("").map { |piece| COMPLEMENTS[piece] }.join.gsub("T", "U")
  end

  def self.of_rna(rna)
    rna.split("").map { |piece| COMPLEMENTS[piece] }.join
  end

end
