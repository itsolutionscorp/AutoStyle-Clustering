class Complement

  @@dna = 'CGTA'
  @@rna = 'GCAU'

  def self.of_dna(sequence)
    sequence.tr(@@dna, @@rna)
  end

  def self.of_rna(sequence)
    sequence.tr(@@rna, @@dna) 
  end

end
