class Complement
  DNA_NT_VALS = 'GCTA'
  RNA_NT_VALS = 'CGAU'

  def self.of_dna(dna_in)
    dna_in.tr(DNA_NT_VALS, RNA_NT_VALS)
  end

  def self.of_rna(rna_in)
    rna_in.tr(RNA_NT_VALS, DNA_NT_VALS)
  end
end
