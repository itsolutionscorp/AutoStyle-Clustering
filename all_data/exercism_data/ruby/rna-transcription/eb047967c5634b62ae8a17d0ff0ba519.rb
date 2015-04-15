class Complement
  TABLE = ["CGTA" , "GCAU"]

  def self.of_dna(strand)
    strand.tr(*TABLE)
  end

  def self.of_rna(strand)
    strand.tr(*TABLE.reverse)
  end
end
