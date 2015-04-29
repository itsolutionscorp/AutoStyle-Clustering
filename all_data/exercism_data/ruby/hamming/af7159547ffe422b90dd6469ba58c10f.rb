class Hamming
  def compute(dna_1, dna_2)
    # Get the minimum length (length of the shortest string)
    min_len = [dna_1.length, dna_2.length].min
    
    # Get the first min_len chars from each string
    dna_1.slice!(0..min_len-1)
    dna_2.slice!(0..min_len-1)
    
    hamming_distance = 0
    dna_1.each_index do |i|
      dna_1[i] == dna_2[i] ? hamming_distance++ : hamming_distance
    end
    
    return hamming_distance
  end
end
