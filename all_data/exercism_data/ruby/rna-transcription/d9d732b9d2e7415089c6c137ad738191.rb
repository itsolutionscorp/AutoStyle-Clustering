class Complement

  @dna = ["G","C","T","A"]
  @rna = ["C","G","A","U"]

  def self.of_dna(complement)
    @dna_strand = complement.split("")
    @rna_complement = []

    @dna_strand.each do |nucleotide|
    dna_value = @dna.index(nucleotide)
    @rna_complement.push(@rna[dna_value, 1])
    end

    @rna_complement.join
  end

  def self.of_rna(complement)
    @rna_strand = complement.split("")
    @dna_complement = []

    @rna_strand.each do |nucleotide|
    rna_value = @rna.index(nucleotide)
    @dna_complement.push(@dna[rna_value, 1])
    end

    @dna_complement.join
  end

end
