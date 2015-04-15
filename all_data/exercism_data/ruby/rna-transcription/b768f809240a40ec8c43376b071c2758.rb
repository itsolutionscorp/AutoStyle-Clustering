class Complement

  DNA_VALUES = ['G', 'C', 'T', 'A']
  RNA_VALUES = ['C', 'G', 'A', 'U']

  def self.of_dna(dna)
    dna.split('').map { |l| RNA_VALUES[DNA_VALUES.index(l)] }.join
  end

  def self.of_rna(rna)
    rna.split('').map { |l| DNA_VALUES[RNA_VALUES.index(l)] }.join
  end

end
