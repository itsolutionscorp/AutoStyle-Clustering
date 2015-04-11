class Complement
  RNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(rna)
    rna.chars.map { |strand| RNA[strand] }.join
  end

  def self.of_rna(rna)
    rna.chars.map {|strand| RNA.invert[strand] }.join
  end
end
