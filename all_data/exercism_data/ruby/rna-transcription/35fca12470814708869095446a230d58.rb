
class Complement

  @@dna = ['G', 'C', 'T', 'A']
  @@rna = ['C', 'G', 'A', 'U']

  def self.of_dna(dna)
    dna.each_char.collect { |nucleotide|
      @@rna[@@dna.index(nucleotide)]
    }.join
  end

  def self.of_rna(rna)
    rna.each_char.collect { |nucleotide|
      @@dna[@@rna.index(nucleotide)]
    }.join
  end

end
