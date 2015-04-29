class Complement
  def self.of_dna(s)
    return s.tr('GCTA', 'CGAU')
  end

  def self.of_rna(s)
    return s.tr('CGAU', 'GCTA')
  end
end
