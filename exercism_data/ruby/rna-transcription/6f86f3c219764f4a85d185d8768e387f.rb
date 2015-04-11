class Complement
  def self.of_dna(n)
    n.tr!('GCTA', 'CGAU')
  end

  def self.of_rna(n)
    n.tr!('CGAU', 'GCTA')
  end
end
