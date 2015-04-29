class Complement
  @@nucleotides_in_dna = 'GCTA'
  @@nucleotides_in_rna = 'CGAU'

  def self.of_dna(dna)
    dna.tr(@@nucleotides_in_dna, @@nucleotides_in_rna)
  end

  def self.of_rna(rna)
    rna.tr(@@nucleotides_in_rna, @@nucleotides_in_dna)
  end
end
