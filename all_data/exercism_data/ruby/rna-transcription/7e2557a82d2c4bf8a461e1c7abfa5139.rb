class Complement

  DNA_COMPLEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  COMPLEMENTS = {
    dna: DNA_COMPLEMENTS,
    rna: DNA_COMPLEMENTS.invert
  }

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
    complements = COMPLEMENTS[@type]
    from = complements.keys.join
    to =   complements.values.join
    strand.tr(from, to)
  end

end
