class Complement
  def self.of_dna(dna)
    dna.chars.map { |dna_char| dna_lookup_hash[dna_char] }.join("")
  end

  def self.of_rna(dna)
    dna.chars.map { |dna_char| dna_lookup_hash.invert[dna_char] }.join("")
  end

  def self.dna_lookup_hash
    @dna_lookup_hash ||= {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end
end
