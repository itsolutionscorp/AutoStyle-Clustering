class Complement

  DNA_MAP = 'GCTA'
  RNA_MAP = 'CGAU'

  def self.of_dna(dna_strand)
    dna_strand.tr(DNA_MAP, RNA_MAP)
  end

  def self.of_rna(rna_strand)
    rna_strand.tr(RNA_MAP, DNA_MAP)
  end

end
