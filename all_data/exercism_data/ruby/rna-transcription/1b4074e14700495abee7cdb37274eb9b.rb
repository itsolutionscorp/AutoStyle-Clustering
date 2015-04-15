class Complement
  PAIRS = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(dna)
    dna.chars.map do |char|
      PAIRS[char]
    end.join
  end

  def self.of_rna(rna)
    rna.chars.map do |char|
      PAIRS.invert[char]
    end.join
  end
end
