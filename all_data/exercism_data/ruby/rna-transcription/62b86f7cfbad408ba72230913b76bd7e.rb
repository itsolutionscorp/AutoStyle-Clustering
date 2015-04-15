class Complement

  MAP = ['GCTA', 'CGAU']

  def self.of_dna(strand)
    strand.tr(*MAP)
  end

  def self.of_rna(strand)
    strand.tr(*MAP.reverse)
  end

end
