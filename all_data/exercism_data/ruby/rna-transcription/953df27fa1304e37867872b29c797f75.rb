class Complement

  DNA_COMPLEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(strand)
    new(:dna).transcribe(strand)
  end

  def self.of_rna(strand)
    new(:rna).transcribe(strand)
  end

  def initialize(type)
    @type = type
  end

  def transcribe(strand)
    strand.chars.map { |nucleotide| complement_of(nucleotide) }.join
  end

  private

  def complement_of(nucleotide)
    complements[@type][nucleotide]
  end

  def complements
    @complements ||= {
      dna: DNA_COMPLEMENTS,
      rna: DNA_COMPLEMENTS.invert
    }
  end

end
