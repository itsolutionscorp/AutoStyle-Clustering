class Complement
  def self.of_dna(strand)
    new(strand).of_dna
  end

  def self.of_rna(strand)
    new(strand).of_rna
  end

  def initialize(strand)
    @strand = strand
  end

  def of_dna
    @of_dna ||= complement_from(dna_to_rna)
  end

  def of_rna
    @of_rna ||= complement_from(rna_to_dna)
  end

  private

  def complement_from(direction)
    @strand.gsub(/./, direction)
  end

  def dna_to_rna
    @dna_to_rna ||= { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  end

  def rna_to_dna
    @rna_to_dna ||= dna_to_rna.invert
  end
end
