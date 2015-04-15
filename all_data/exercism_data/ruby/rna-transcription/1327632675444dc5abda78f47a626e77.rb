class Complement
  DNA_TO_RNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    strand.chars.map do |nucleotide|
      dna_invert(nucleotide)
    end.join
  end

  def self.of_rna(strand)
    strand.chars.map do |nucleotide|
      rna_invert(nucleotide)
    end.join
  end

  def self.dna_invert(nucleotide)
    DNA_TO_RNA.fetch(nucleotide) { raise ArgumentError }
  end

  def self.rna_invert(nucleotide)
    RNA_TO_DNA.fetch(nucleotide) { raise ArgumentError }
  end
end
