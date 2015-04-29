class Hamming
  def initialize
  end
  
  def self.compute(dna_1, dna_2)
    # Get the minimum length (length of the shortest string)
    min_len = [dna_1.length, dna_2.length].min
    
    # Get the first min_len chars from each string
    dna_1.slice!(0..min_len-1).upcase!
    dna_2.slice!(0..min_len-1).upcase!
    
    hamming_distance = 0
    if min_len == 1
      dna_1[0] != dna_2[0] ? ++hamming_distance : hamming_distance
    else
      dna_1.split("").each_index do |i|
        dna_1[i] != dna_2[i] ? ++hamming_distance : hamming_distance
      end
    end
    
    return hamming_distance
  end
end
