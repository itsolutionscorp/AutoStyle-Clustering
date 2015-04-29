class Hamming
  def self.compute(a, b) 
    dna_strand1 = a.chars
    dna_strand2 = b.chars
    normalize(dna_strand1, dna_strand2)
    #return a count of how many elemets are different
    (0...@amount_to_compare).count do |nucleotide| 
      dna_strand1[nucleotide] != dna_strand2[nucleotide]
    end
  end
  #determine how many elements to iterate over
  def self.normalize(array1, array2)
      @amount_to_compare = [array1.length, array2.length].min  
  end
end
