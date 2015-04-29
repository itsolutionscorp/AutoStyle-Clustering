class Complement
  def self.dna_to_rna
    { "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U" }
  end

  def self.of_dna(s)
    s.chars.map do |n|
      dna_to_rna[n]
    end.join
  end

  def self.of_rna(s)
    rna_to_dna = dna_to_rna.invert
    s.chars.map do |n|
      rna_to_dna[n]
    end.join
  end

end
