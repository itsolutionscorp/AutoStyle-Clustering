class Complement
  DNA_RNA_MAP = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(sequence)
    transpose(sequence, DNA_RNA_MAP)
  end

  def self.of_rna(sequence)
    transpose(sequence, DNA_RNA_MAP.invert)
  end

  private

  def self.transpose(sequence, dictionary)
    sequence.tr(dictionary.keys.join, dictionary.values.join)
  end

end
