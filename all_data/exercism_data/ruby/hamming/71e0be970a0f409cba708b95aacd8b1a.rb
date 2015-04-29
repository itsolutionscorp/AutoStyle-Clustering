class Hamming

  def self.compute( dna_1, dna_2 )
    dna_1_array = dna_1.split(//)
    dna_2_array = dna_2.split(//)
    
    hamming_distance = 0
    
    x = 0
    
    dna_1_array_count = dna_1_array.count
    dna_2_array_count = dna_2_array.count
    
    if dna_1_array_count < dna_2_array_count
      smaller_array = dna_1_array
    
    else dna_1_array_count > dna_2_array_count
      smaller_array = dna_2_array
    
    end
    
    
    
    smaller_array.each do |i|
      
      if dna_1_array[x] != dna_2_array[x]
        hamming_distance += 1
      
      end
      
    x += 1
     
    end
    
    hamming_distance
    
  end
end
