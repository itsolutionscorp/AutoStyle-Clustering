class Complement
  def self.of_dna(strand)
    strand.tr("CTAG", "GAUC")
  end

  def self.of_rna(strand)
    strand.tr("GAUC", "CTAG")
  end
end
