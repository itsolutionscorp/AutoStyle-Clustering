class Complement
  RNA = {
    "C" => "G",
    "G" => "C",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(dna)
    dna.chars.map { |piece| RNA[piece] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |piece| RNA.invert[piece] }.join
  end
end
