module Complement
  extend self

  BASE_PAIRS = {
     'G'=>'C',
     'C'=>'G',
     'T'=>'A',
     'A'=>'U'
    }

  def of_dna(rna_strain)
    rna_strain.split('').map do |s|
      BASE_PAIRS[s] 
    end.join
  end

  def of_rna(dna_strain)
    dna_strain.split('').map do |s|
      BASE_PAIRS.invert[s]
    end.join
  end
end
