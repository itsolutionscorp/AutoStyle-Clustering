class Complement
  def self.complements
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end

  def self.of_dna(strand)
    strand.chars.map { |nucleotide| complements[nucleotide] }.join
  end

  def self.of_rna(strand)
    strand.chars.map { |nucleotide| complements.key(nucleotide) }.join
  end
end
