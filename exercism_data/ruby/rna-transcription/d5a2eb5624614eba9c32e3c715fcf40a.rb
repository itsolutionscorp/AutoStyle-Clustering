class DNA < Struct.new(:dna_string)
  def to_rna
    RNA.dna_to_rna(dna_string)
  end

  def self.rna_to_dna(rna_string)
    rna_string.tr('U', 'T')
  end
end

class RNA < Struct.new(:rna_string)
  def to_dna
    DNA.rna_to_dna(rna_string)
  end

  def self.dna_to_rna(dna_string)
    dna_string.tr('T', 'U')
  end
end
