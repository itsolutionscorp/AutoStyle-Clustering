module Hamming
  def self.compute(strand1, strand2)
    paired_genes = self.pair_genes(strand1, strand2)
    paired_genes.count{ |gene1, gene2| gene1 != gene2 }
  end

  def self.pair_genes(strand1, strand2)
    strand1.chars.zip(strand2.chars).reject{ |pair| pair.include?(nil) }
  end
end
