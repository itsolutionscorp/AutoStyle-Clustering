class Complement

  def self.of_dna(dna)
    string = dna
    complement = string.chars.collect { |newc| complements[newc] }.join
  end

  def self.of_rna(rna)
    rna = rna
    dna = rna.chars.map { |nucleotide| complements.invert[nucleotide] }.join
  end

  def self.complements
    { "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U"}
  end
end
