# transcribe DNA strand to RNA strand and the reverse. Conversion is:
# dna  rna
# G -> C
# C -> G
# T -> A
# A -> U

class Complement
  def self.of_dna(dna_strand)
    dna_strand.tr('GCTA','CGAU')
  end

  def self.of_rna(rna_strand)
    rna_strand.tr('CGAU','GCTA')
  end
end
