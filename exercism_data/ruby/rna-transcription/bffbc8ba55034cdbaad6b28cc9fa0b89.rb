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
      DNA_TO_RNA[nucleotide]
    end.join
  end

  def self.of_rna(strand)
    strand.chars.map do |nucleotide|
      RNA_TO_DNA[nucleotide]
    end.join
  end
end