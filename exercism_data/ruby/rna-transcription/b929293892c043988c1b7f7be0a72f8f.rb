class Complement
  def self.of_dna(dna)
    return dna.chars.map do |nucleotide|
      complements_of_dna[nucleotide]
    end.join
  end

  def self.of_rna(dna)
    return dna.chars.map do |nucleotide|
      complements_of_dna.invert[nucleotide]
    end.join
  end

private

  def self.complements_of_dna
    {"G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"}
  end
end
