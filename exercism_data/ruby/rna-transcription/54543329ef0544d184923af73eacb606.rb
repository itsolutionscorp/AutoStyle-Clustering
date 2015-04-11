class DNA
  def initialize(dna)
       @dna = dna
  @nucleotides = @dna.split("")
  end

  def to_rna
    @nucleotides.inject("") do |dna, nucleotide|
         if nucleotide == "T"
            dna += "U"
         else
            dna += nucleotide
         end
    end
  end
end
