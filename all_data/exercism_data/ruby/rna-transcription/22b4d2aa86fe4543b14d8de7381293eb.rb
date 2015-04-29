class Complement
  @DNA_TO_RNA = ['GCAT', 'CGUA']
  @RNA_TO_DNA = @DNA_TO_RNA.reverse

  def self.of_dna(strands)
    strands.tr(*@DNA_TO_RNA)
  end

  def self.of_rna(strands)
    strands.tr(*@RNA_TO_DNA)
  end
end
