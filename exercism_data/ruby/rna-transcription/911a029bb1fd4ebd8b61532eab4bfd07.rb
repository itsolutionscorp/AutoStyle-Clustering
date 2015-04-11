class Complement

  @@dna_map = 'GCTA'
  @@rna_map = 'CGAU'

  def self.of_dna(dna_strand)
    dna_strand.tr(@@dna_map, @@rna_map)
  end

  def self.of_rna(rna_strand)
    rna_strand.tr(@@rna_map, @@dna_map)
  end

end
