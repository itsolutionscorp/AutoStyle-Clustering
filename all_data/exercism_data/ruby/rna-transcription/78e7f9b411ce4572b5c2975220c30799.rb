class Complement
  DNA_TO_RNA = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    validate!(strand, DNA_TO_RNA)
    new(strand).of_dna
  end

  def self.of_rna(strand)
    validate!(strand, RNA_TO_DNA)
    new(strand).of_rna
  end

  def self.validate!(strand, map)
    raise ArgumentError unless (strand.chars - map.keys).empty?
  end

  def initialize(strand)
    @strand = strand
  end

  def of_dna
    @of_dna ||= complement_from(DNA_TO_RNA)
  end

  def of_rna
    @of_rna ||= complement_from(RNA_TO_DNA)
  end

  private

  def complement_from(direction)
    @strand.gsub(/./, direction)
  end
end
