class Complement

  def self.of_dna(strand)
    strand.tr('AUGTC', 'UACAG')
  end


  def self.of_rna(strand)
    strand.tr('AUGC', 'TACG')
  end
end
