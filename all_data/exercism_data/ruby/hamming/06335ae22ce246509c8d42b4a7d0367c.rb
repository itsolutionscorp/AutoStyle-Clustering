class Hamming
  def self.compute(strand_a, strand_b)
    pair_strands(strand_a, strand_b)
      .count { |g| different_genes g[0], g[1] }
  end

  def self.different_genes(gene_a, gene_b)
    gene_a != gene_b
  end

  def self.pair_strands strand_a, strand_b
    strand_a.chars
      .zip(strand_b.chars)
      .reject { |genes| genes.first == nil || genes.last == nil }
  end
end
