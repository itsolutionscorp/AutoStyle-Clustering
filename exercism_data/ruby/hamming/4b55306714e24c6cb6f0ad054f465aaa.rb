class Hamming
  def self.compute(strand_a, strand_b)
    pair_strands(strand_a, strand_b)
      .count { |g| different_genes g }
  end

  def self.different_genes(gene_pair)
    gene_pair.first != gene_pair.last
  end

  def self.pair_strands strand_a, strand_b
    strand_a.chars
      .zip(strand_b.chars)
      .reject { |genes| genes.any?(&:nil?) }
  end
end
