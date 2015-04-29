class Complement
  # Hash containing DNA/RNA nucleotide pairings
  DNA_RNA =
  {
    "C" => "G",
    "G" => "C",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(strand)
    strand.gsub /[CGTA]/, DNA_RNA
  end

  def self.of_rna(strand)
    strand.gsub /[GCAU]/, DNA_RNA.invert
  end
end
