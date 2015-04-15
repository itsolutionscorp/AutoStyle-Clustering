class Complement
  @dna = "GCTA"
  @rna = "CGAU"


  def self.of_dna(dna_sequence)
  	dna_sequence.tr(@dna, @rna)
  end

  def self.of_rna(rna_sequence)
    rna_sequence.tr(@rna, @dna)
  end

end
