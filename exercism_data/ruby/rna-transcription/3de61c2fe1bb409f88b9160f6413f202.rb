class Complement
  def self.of_dna(rna)
    transcribe(rna, 'CGTA', 'GCAU', 'U')
  end

  def self.of_rna(dna)
    transcribe(dna, 'CGUA', 'GCAT', 'T')
  end

  def self.transcribe(genome_type, bases, complement, incompatible)
    fail ArgumentError if genome_type.include?(incompatible)
    genome_type.tr(bases, complement)
  end
end
