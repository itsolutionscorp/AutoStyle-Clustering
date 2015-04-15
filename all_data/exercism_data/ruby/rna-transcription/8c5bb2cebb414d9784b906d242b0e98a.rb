class Complement

  DNA_to_RNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  RNA_to_DNA = DNA_to_RNA.invert

  def self.of_dna(string)
    string.chars.map {|c| DNA_to_RNA[c] }.join
  end

  def self.of_rna(string)
    string.chars.map {|c| RNA_to_DNA[c] }.join
  end

end
