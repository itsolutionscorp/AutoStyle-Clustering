class Hamming
  def self.compute(strand_a, strand_b)
    strand_a_array = strand_a.chars
    strand_b_array = strand_b.chars
    non_matching_letter_count = 0
    strand_a_array.each_with_index do |letter, index|
      if strand_b_array[index] != letter
        non_matching_letter_count += 1
      end
    end
    
    non_matching_letter_count    
  end
end
