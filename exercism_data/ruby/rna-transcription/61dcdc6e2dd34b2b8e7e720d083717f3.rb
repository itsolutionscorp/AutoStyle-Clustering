class Complement

  def self.of_dna(nucleotide)
    nucleotide.tr(GCTA,CGAU)
  end

  def self.of_rna(nucleotide)
    nucleotide.tr(GCTA,CGAT)
  end
end
