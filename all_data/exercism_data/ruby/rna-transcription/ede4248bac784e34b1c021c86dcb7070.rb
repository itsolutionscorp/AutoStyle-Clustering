class Complement
  def self.dna_to_rna
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end

  def self.rna_to_dna
    {
      "C" => "G",
      "G" => "C",
      "A" => "T",
      "U" => "A"
    }
  end

  def self.of_dna(dna)
    dereference(dna, Complement.dna_to_rna)
  end

  def self.of_rna(rna)
    dereference(rna, Complement.rna_to_dna)
  end

  def self.dereference(keys, mapping)
    keys.chars.map { |char| mapping[char] }.join
  end
end
