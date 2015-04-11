class Complement

  DNA_TO_RNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U",
  }

  def self.of_dna(strand)
    strand.tr(DNA_TO_RNA.keys.join, DNA_TO_RNA.values.join)
  end

  def self.of_rna(strand)
    strand.tr(DNA_TO_RNA.values.join, DNA_TO_RNA.keys.join)
  end
end
