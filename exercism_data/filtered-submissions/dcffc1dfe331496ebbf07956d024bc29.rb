class Hamming

  def compute(gene_1, gene_2)

    gene_1.chars.zip(gene_2.chars)
      .count do |ch1, ch2|
      ch1 != ch2 && ch2
    end

  end
end
