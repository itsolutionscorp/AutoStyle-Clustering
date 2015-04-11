class Complement
  DNA_BASES = 'GCTA'
  RNA_BASES = 'CGAU'

  def self.of_dna(strand)
    strand.tr(DNA_BASES, RNA_BASES)
  end

  def self.of_rna(strand)
    strand.tr(RNA_BASES, DNA_BASES)
  end
end
