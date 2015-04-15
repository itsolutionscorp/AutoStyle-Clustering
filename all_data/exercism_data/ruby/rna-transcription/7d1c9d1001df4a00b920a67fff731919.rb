module Complement
  DNA_SEQUENCE = 'GCTA'
  RNA_SEQUENCE = 'CGAU'

  module_function
  def of_dna strand
    strand.upcase.tr(DNA_SEQUENCE, RNA_SEQUENCE)
  end

  def of_rna strand
    strand.upcase.tr(RNA_SEQUENCE, DNA_SEQUENCE)
  end
end
