class Hamming

  def Hamming.compute (dna1, dna2)
    dna1.chars.zip(dna2.chars).count {|x| x[0] != x[1] && x[1] != nil}
  end

end
