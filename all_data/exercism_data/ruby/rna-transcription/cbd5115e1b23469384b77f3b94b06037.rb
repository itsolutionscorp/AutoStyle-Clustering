class Complement
  def self.complements
    { "C" => "G", "G" => "C", "T" => "A", "A" => "U"}
  end

  def self.translate(string, complements)
    conversion = string.chars.map {|e| complements[e]}
    conversion.flatten.join
  end

  def self.of_dna(dna)
    translate(dna, complements)
  end

  def self.of_rna(rna)
    translate(rna, complements.invert)
  end
end
