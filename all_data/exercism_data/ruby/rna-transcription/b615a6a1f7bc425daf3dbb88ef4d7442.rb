class Complement
  @DNA_TO_RNA = ['GCAT', 'CGUA']

  def self.of_dna(strands)
    strands.tr(*@DNA_TO_RNA)
  end

  def self.of_rna(strands)
    strands.tr(*@DNA_TO_RNA.reverse)
  end
end
