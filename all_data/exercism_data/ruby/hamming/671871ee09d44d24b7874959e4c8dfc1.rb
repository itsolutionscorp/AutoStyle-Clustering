class Hamming

  def Hamming.compute (dna1, dna2)
  	count = 0
    dna1.bytes.zip(dna2.bytes).each do |x|
      count += 1 if x[0] != x[1] && x[1] != nil
    end
    return count
  end

end
