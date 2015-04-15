class Hamming
  def self.compute(a, b) 
    dna_strand1 = a.chars
    dna_strand2 = b.chars
    find_common_length(dna_strand1, dna_strand2).times.count do |nucleotide_position| 
      dna_strand1[nucleotide_position] != dna_strand2[nucleotide_position]
    end
  end
  def self.find_common_length(array1, array2)
     [array1.length, array2.length].min  
  end
end
