class Complement

  DNA_TO_RNA_MAPPING = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  RNA_TO_DNA_MAPPING = DNA_TO_RNA_MAPPING.invert

  def self.of_dna(dna)
    convert(dna, DNA_TO_RNA_MAPPING)
  end

  def self.of_rna(rna)
    convert(rna, RNA_TO_DNA_MAPPING)
  end

  def self.convert(strand, mapping)
    strand.split("").map{|n| mapping[n]}.join("")
  end

end
