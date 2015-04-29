class Complement
  DNA_TO_RNA_MAPPING = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA_MAPPING = {
    'C' => 'G',
    'G' => 'C',
    'U' => 'A',
    'A' => 'T'
  }

  def self.of_dna(dna_strand)
    new(dna_strand).convert(DNA_TO_RNA_MAPPING)
  end

  def self.of_rna(rna_strand)
    new(rna_strand).convert(RNA_TO_DNA_MAPPING)
  end

  def initialize(strand)
    @strand = strand
  end

  def convert(mapping)
    nucleotides.map { |nucleotide| mapping.fetch(nucleotide) }.join
  end

  private
  attr_reader :strand

  def nucleotides
    strand.split("")
  end
end
