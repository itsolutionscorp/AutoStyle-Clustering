class Complement
  @dna_to_rna_pairs = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }
  @rna_to_dna_pairs = {
    "C" => "G",
    "G" => "C",
    "A" => "T",
    "U" => "A"
  }

  def self.of_dna(dna_string)
    rna_output = ""
    dna_string.each_char do |nucleotide|
      rna_output << @dna_to_rna_pairs[nucleotide]
    end
    rna_output
  end

  def self.of_rna(rna_string)
    dna_output = ""
    rna_string.each_char do |nucleotide|
      dna_output << @rna_to_dna_pairs[nucleotide]
    end
    dna_output
  end
end
