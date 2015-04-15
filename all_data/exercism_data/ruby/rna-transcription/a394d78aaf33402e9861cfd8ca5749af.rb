class Complement
  DNA_TO_RNA_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    new(strand, DNA_TO_RNA_MAP).to_complement
  end

  def self.of_rna(strand)
    new(strand, DNA_TO_RNA_MAP.invert).to_complement
  end

  def initialize(strand, map)
    @strand = strand
    @map    = map
  end

  def to_complement
    strand.chars.each_with_object('') do |nucleotide, complement|
      complement << transcribe(nucleotide)
    end
  end

  private

  attr_reader :strand, :map

  def transcribe(nucleotide)
    map.fetch(nucleotide)
  end
end
