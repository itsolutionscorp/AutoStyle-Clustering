class Complement
  DNA_TO_RNA_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    new(strand, :dna).to_complement
  end

  def self.of_rna(strand)
    new(strand, :rna).to_complement
  end

  def initialize(strand, source)
    @strand = strand
    @source = source
  end

  def to_complement
    strand.chars.each_with_object('') do |nucleotide, complement|
      complement << transcribe(nucleotide)
    end
  end

  private

  attr_reader :strand, :source

  def transcribe(nucleotide)
    map.fetch(nucleotide)
  end

  def map
    case source
    when :dna
      DNA_TO_RNA_MAP
    when :rna
      DNA_TO_RNA_MAP.invert
    else
      {}
    end
  end
end
