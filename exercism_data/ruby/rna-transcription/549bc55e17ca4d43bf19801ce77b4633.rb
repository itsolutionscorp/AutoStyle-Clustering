class Complement
  
  DNA_SUBSTITUTION  = {'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U'}
  RNA_SUBSTITUTION  = DNA_SUBSTITUTION.invert
  @dna, @rna = 'CGTA', 'GCAU'


  def self.of_dna(sequence)
    sequence.tr @dna, @rna
  end

  def self.of_rna(sequence)
    sequence.tr @rna, @dna
  end
end
