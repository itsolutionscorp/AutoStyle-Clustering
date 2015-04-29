module Complement
  module_function
  def of_dna strand
    strand.upcase.tr('GCTA', 'CGAU')
  end

  def of_rna strand
    strand.upcase.tr('CGAU', 'GCTA')
  end
end
