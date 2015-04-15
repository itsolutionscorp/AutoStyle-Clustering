class DNA
  def initialize(dna)
    @dna = dna
  end

  def to_rna
      thymine = 'T'
      uracil = 'U'

      @dna.gsub(thymine, uracil)
  end
end
