class Complement
  def self.of_dna(s)
    s.tr('GCTA', 'CGAU')
  end

  def self.of_rna(s)
    s.tr('CGAU', 'GCTA')
  end
end
