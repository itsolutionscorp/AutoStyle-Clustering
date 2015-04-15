class Complement
  def self.of_dna dna
    dna.chars.map { |nucleotide| complement_dna_nucleotide(nucleotide) }.join
  end

  def self.of_rna rna
    rna.chars.map { |nucleotide| complement_rna_nucleotide(nucleotide) }.join
  end

  def self.complement_rna_nucleotide(rna)
    {
        "C" => "G",
        "U" => "A",
        "A" => "T",
        "G" => "C"
    }.fetch(rna)
  end

  def self.complement_dna_nucleotide(dna)
    {
      "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U",
    }.fetch(dna)
  end
end
