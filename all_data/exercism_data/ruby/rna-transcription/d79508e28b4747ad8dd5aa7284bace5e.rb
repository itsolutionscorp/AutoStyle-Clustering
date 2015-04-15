class Complement
  def self.of_dna(dna)
    dna_complemented = dna
    dna_complemented.gsub(/[GCTA]/, "G" => "C", "C" => "G", "T" => "A", "A" => "U")
  end
  def self.of_rna(rna)
    rna_complemented = rna
    rna_complemented.gsub(/[CGAU]/, "C" => "G", "G" => "C", "A" => "T", "U" => "A")
  end
end
