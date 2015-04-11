class Complement

  def self.dna_pairs
    {"C" => "G", "G" => "C", "T" => "A", "A" => "U", "U" => "A"}
  end

  def self.rna_pairs
    {"C" => "G", "G" => "C", "T" => "A", "A" => "T", "U" => "A"}
  end

  def self.of_dna(nucleotide)
    calculate(dna_pairs, nucleotide)
  end

  def self.of_rna(nucleotide)
    calculate(rna_pairs, nucleotide)
  end

  def self.calculate(pairs, strand)
    strand.chars.map { |char| pairs[char] }.join
  end

end
