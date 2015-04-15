class Complement
  def self.of_dna(strand)
    translate(strand, dna_to_rna)
  end

  def self.of_rna(strand)
    translate(strand, dna_to_rna.invert)
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

  def self.translate(strand, mapping)
    strand.chars.map{|c| mapping[c]}.join
  end
end
