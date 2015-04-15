class Hamming

  def self.compute(dna_a , dna_b)
    hamming_value=0
    array_a = dna_a.split(//)
    array_b = dna_b.split(//)
    array_a.each_with_index  do|a,i|
    	hamming_value++ if !a.eql?(array_b[i]) and !array_b[i].nil?
    end
    return hamming_value
  end

end
