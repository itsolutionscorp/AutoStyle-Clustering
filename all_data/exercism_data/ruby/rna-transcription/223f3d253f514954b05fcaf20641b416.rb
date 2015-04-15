class Complement
  TRANSLATOR = ['GCTA','CGAU']

  def self.of_dna(strand)
    strand.tr(*TRANSLATOR)
  end

  def self.of_rna(strand)
    strand.tr(*TRANSLATOR.reverse)
  end

end
