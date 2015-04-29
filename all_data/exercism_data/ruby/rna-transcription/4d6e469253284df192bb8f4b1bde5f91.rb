class Complement
  COMPLEMENTS = ['GCTA', 'CGAU']

  def self.of_dna(sequence)
    sequence.tr(*COMPLEMENTS)
  end

  def self.of_rna(sequence)
    sequence.tr(*COMPLEMENTS.reverse)
  end
end
