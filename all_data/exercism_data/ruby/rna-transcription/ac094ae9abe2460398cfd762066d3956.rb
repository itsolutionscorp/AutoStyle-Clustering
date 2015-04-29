class Complement
  DNA_RNA_MAP = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(sequence)
    map(sequence, DNA_RNA_MAP)
  end

  def self.of_rna(sequence)
    map(sequence, DNA_RNA_MAP.invert)
  end

  private

  def self.map(sequence, dictionary)
    sequence.chars.map do |nucleotide|
      dictionary[nucleotide]
    end.join
  end

end
