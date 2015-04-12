class Hamming
  def compute(gene_a, gene_b)
    hamming_distance = 0

    array_a = gene_a.split("")
    array_b = gene_b.split("")
    
    array_a.zip(array_b).each do |a, b|
      puts a,b
      if a != b
        hamming_distance = hamming_distance + 1
      end
    end
    hamming_distance
  end
end
