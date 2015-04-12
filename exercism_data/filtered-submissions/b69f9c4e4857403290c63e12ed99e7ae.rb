class Hamming
  def compute(dna1, dna2)
    #zip(dna1,dna2).reduce(:+)
    #return 0 if dna1 == '' || dna2 == ''
    dna1.chars.zip(dna2.chars).count{|a,b| a != b && a && b}
  end
end
