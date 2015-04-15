class Complement

  def self.of_dna(nucleotide)
    nucleotide.tr('ACGT', 'UGCA')
  end

  def self.of_rna(nucleotide)
    nucleotide.tr('UGCA', 'ACGT')
  end

end
