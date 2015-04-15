class Complement

  def self.of_dna(dna)
    string = dna
    complement = string.chars.collect { |n| complements[n] }.join
  end

  def self.of_rna(rna)
    dna = rna.chars.map { |n| complements.invert[n] }.join
  end

  def self.complements
    { "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U"}
  end
end
