class Hamming
  def self.compute(strand_a, strand_b)
    len = matching_strand_size strand_a, strand_b

    len.times.to_a.reduce(0) do |result, ind|
      result += base_difference strand_a[ind], strand_b[ind]
    end
  end

  def self.base_difference(gene_a, gene_b)
    gene_a == gene_b ? 0 : 1
  end

  def self.matching_strand_size(strand_a, strand_b)
    [strand_a, strand_b].min_by {|s| s.size}.size
  end
end
