class Complement
  @dna = "GCTA"
  @rna = "CGAU"

  def self.of_dna(strand)
    strand.tr(@dna, @rna)
  end

  def self.of_rna(strand)
    strand.tr(@rna, @dna)
  end
end
