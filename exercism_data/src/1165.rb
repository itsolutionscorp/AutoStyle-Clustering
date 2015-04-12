class Hamming
  
  def compute(str1,str2)
    
    # Determine length of strands to analyze.
    shortest_strand_length = str1.length <= str2.length ? str1.length : str2.length
    hamming_distance = 0
    
    # Iterate over selected portion of each strand counting discrepancies.
    (0...shortest_strand_length).each do |i|
      hamming_distance += 1 unless str1[i] == str2[i]
    end
    
    hamming_distance
  end
end
