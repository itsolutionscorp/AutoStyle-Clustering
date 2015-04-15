class Hamming

  def self.compute(gene_1, gene_2)

    gene_1.chars.zip(gene_2.chars)
      .count do |nucleotide_1, nucleotide_2|
      nucleotide_1 != nucleotide_2 && nucleotide_2
    end

  end
end
