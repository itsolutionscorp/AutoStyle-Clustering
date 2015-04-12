class Hamming

  def compute(geneOne,geneTwo)
    hammingDistance = 0
    gene1 = geneOne.size
    gene2 = geneTwo.size
    gene = [gene1,gene2].min - 1
    0.upto(gene) do |g|
      if geneOne[g] != geneTwo[g]
        hammingDistance += 1
      end
    end
    return hammingDistance
  end

end
