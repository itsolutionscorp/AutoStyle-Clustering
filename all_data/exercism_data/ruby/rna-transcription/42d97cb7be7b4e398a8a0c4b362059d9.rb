class Complement
  DNA_RNA = {
    "C" => "G",
    "G" => "C",
    "T" => "A",
    "A" => "U"
  }

  RNA_DNA = DNA_RNA.invert

  def self.of_dna(strand)
    new(DNA_RNA).transcribe(strand)
  end

  def self.of_rna(strand)
    new(RNA_DNA).transcribe(strand)
  end

  def initialize(complement_table)
    @complement_table = complement_table
  end

  def transcribe(strand)
    strand.chars.map { |nucleotide| @complement_table.fetch(nucleotide) }.join
  end
end
