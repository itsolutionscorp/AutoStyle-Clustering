class Complement

  DNA_TO_RNA_MAPPING = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  RNA_TO_DNA_MAPPING = DNA_TO_RNA_MAPPING.invert

  def self.of_dna(dna)
    dna.split("").map{|n| DNA_TO_RNA_MAPPING[n]}.join("")
  end

  def self.of_rna(rna)
    rna.split("").map{|n| RNA_TO_DNA_MAPPING[n]}.join("")
  end

end
