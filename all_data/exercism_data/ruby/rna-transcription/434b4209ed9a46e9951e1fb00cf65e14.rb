class Complement

  def self.of_dna(string)
    string.tr('ACGT', 'UGCA')
  end

  def self.of_rna(string)
    string.tr('UGCA', 'ACGT')
  end

end
