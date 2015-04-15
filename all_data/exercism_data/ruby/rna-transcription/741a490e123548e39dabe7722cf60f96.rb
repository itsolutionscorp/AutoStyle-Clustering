class Complement
  DNA_MAP = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }
  RNA_MAP = DNA_MAP.invert

  def self.of_dna(dna)
    dna.each_char.map { |d| DNA_MAP[d] }.join
  end

  def self.of_rna(rna)
    rna.each_char.map { |d| RNA_MAP[d] }.join
  end
end
