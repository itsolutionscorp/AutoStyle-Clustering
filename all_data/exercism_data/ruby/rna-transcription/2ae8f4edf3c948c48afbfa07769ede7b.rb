class Complement
  @@dna_to_rna = {
    "A" => "U",
    "C" => "G",
    "T" => "A",
    "G" => "C",
  }

  @@rna_to_dna = {
    "A" => "T",
    "C" => "G",
    "U" => "A",
    "G" => "C",
  }

  def self.of_dna(dna)
    dna.each_char.map { |nucleotide| @@dna_to_rna[nucleotide] }.join
  end

  def self.of_rna(rna)
    rna.each_char.map { |nucleotide| @@rna_to_dna[nucleotide] }.join
  end
end
