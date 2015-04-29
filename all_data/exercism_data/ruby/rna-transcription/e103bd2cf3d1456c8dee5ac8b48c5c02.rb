class Complement

  def self.of_dna(dna)
    dna.chars.collect { |n| complements[n] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |n| complements.invert[n] }.join
  end

  def self.complements
    { "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U"}
  end
end
