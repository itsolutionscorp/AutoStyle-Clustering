class Hamming
  def compute(strand_one, strand_two)
    hamming_distance = 0
    
    strand_one.split("").each_with_index do |value, index|
      if value != strand_two[index] && (strand_two[index] != nil)
        hamming_distance += 1
      end
    end
    
    hamming_distance
  end
end
