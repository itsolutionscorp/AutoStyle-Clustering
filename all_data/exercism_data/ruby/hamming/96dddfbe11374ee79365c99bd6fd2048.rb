class Hamming
  def self.compute(dna1, dna2)
    compute_recursion(0, dna1, dna2)
  end

  def self.compute_recursion(index, dna1, dna2)
    return 0 if (dna1[index].nil? or dna2[index].nil?)
    if (dna1[index] == dna2[index])
      return  compute_recursion(index+1, dna1, dna2)
    end
    return 1 + compute_recursion(index+1, dna1, dna2)
  end
end
