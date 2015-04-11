class Hamming
  def self.compute(dna1, dna2)
    #swap(dna1,dna2)
    dna1, dna2 = dna2, dna1 if dna1.size > dna2.size
    dna1.chars.zip(dna2.chars).count{|a,b| a != b}
  end
  
  #def self.swap(d1,d2)
  #  d1,d2 = d2,d1 if d1.size > d2.size
  #end
end
