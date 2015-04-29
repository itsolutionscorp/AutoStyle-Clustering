module Complement
  DNA = 'GCTA'
  RNA = 'CGAU'

  module_function def of_dna(strand)
    strand.tr DNA, RNA
  end

  module_function def of_rna(strand)
    strand.tr RNA, DNA
  end
end
