class Complement
  PAIRS = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(dna)
    dna.each_char.map {|c| PAIRS[c]}.join("")
  end

  def self.of_rna(rna)
    rna.each_char.map {|c| PAIRS.invert[c]}.join("")  
  end
end
