class Complement
  DNA_MAP = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  RNA_MAP = DNA_MAP.invert

  def self.of_dna(input)
    input.split("").map { |c| DNA_MAP[c] }.join
  end

  def self.of_rna(input)
    input.split("").map { |c| RNA_MAP[c] }.join
  end
end
