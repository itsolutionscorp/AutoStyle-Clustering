class Complement
  @dna = 'GCTA'
  @rna = 'CGAU'

  def self.of_dna (dna)
    return dna.to_s.tr(@dna, @rna)
  end

  def self.of_rna (rna)
    return rna.to_s.tr(@rna, @dna)
  end
end
