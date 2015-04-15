class Complement

  def self.of_dna(entry)
    entry.tr("CGTA", "GCAU")
  end

  def self.of_rna(entry)
    entry.tr("CGTAU","GCATA" )
  end
end
