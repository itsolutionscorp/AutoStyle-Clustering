class Complement
  DNA_TO_RNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(seq)
    translate(seq, DNA_TO_RNA)
  end

  def self.of_rna(seq)
    translate(seq, RNA_TO_DNA)
  end

  def self.translate(seq, complements)
    seq.chars.map { |c| complements.fetch(c) }.join
  rescue KeyError => e
    raise(ArgumentError, e.message)
  end
end
