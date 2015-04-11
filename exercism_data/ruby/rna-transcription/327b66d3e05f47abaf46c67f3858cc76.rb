class Complement
  COMPLEMENTS = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "T",
    "U" => "A"
  }

  def self.of_dna(dna)
    dna.split("").map do |piece|
      raise ArgumentError if piece == "U"
      COMPLEMENTS[piece]
    end.join.gsub("T", "U")
  end

  def self.of_rna(rna)
    rna.split("").map do |piece|
      raise ArgumentError if piece == "T"
      COMPLEMENTS[piece]
    end.join
  end

end
