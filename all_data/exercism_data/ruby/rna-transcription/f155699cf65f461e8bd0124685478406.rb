class Complement
  RNA_COMPLEMENTS = {
    "A" => "U",
    "C" => "G",
    "T" => "A",
    "G" => "C"
  }

  DNA_COMPLEMENTS = RNA_COMPLEMENTS.invert

  def self.of_dna(dna)
    transcribe(dna, RNA_COMPLEMENTS)
  end

  def self.of_rna(rna)
    transcribe(rna, DNA_COMPLEMENTS)
  end

  private

  def self.transcribe(sequence, complements)
    sequence.chars.map {|n| complements[n]}.join
  end
end
