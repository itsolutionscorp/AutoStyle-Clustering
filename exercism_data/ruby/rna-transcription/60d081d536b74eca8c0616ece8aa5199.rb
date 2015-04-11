module Complement
  module_function
  
  def of_dna(s)
    s.tr 'GCTA', 'CGAU'
  end
  
  def of_rna(s)
    s.tr 'CGAU', 'GCTA'
  end
end
