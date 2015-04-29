#The four nucleotides found in DNA are
#adenine (**A**), cytosine (**C**),
#guanine (**G**) and thymidine (**T**).

#The four nucleotides found in RNA are
#adenine (**A**), cytosine (**C**),
#guanine (**G**) and uracil (**U**).

class Complement

  def self.of_dna(dna_strand)
    rna = dna_strand.gsub(/[CGTA]/, 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U')
  end

  def self.of_rna(rna_strand)
    dna = rna_strand.gsub(/[GCAU]/, 'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A')
  end
end
