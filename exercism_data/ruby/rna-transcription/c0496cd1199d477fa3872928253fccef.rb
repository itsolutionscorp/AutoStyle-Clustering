class Complement
  def self.of_dna(r)
    r.tr("CGTA", "GCAU");
  end
  def self.of_rna(d)
    d.tr("CGUA", "GCAT");
  end
end
