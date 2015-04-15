class Complement
  RNA_COMPLEMENTS = {
    "A" => "U",
    "C" => "G",
    "T" => "A",
    "G" => "C"
  }

  DNA_COMPLEMENTS = {
    "A" => "T",
    "C" => "G",
    "U" => "A",
    "G" => "C"
  }

  def self.of_dna(dna)
    translate(dna, RNA_COMPLEMENTS)
  end

  def self.of_rna(rna)
    translate(rna, DNA_COMPLEMENTS)
  end

  private

  def self.translate(sequence, complements)
    sequence.chars.map {|n| complements[n]}.join
  end
end
