class Complement
  def self.of_dna(strand)
    strand.chars.map{|c| dna_to_rna[c]}.join
  end

  def self.of_rna(strand)
    strand.chars.map{|c| dna_to_rna.invert[c]}.join
  end

  private
  def self.dna_to_rna
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end
end
