class Hamming
  
  class << self
    
    def compute(strand1, strand2)
      index = -1
      strand1.chars.inject(0) do |distance, char|
        index += 1
        strand_matches_at_index?(strand2, char, index) ? distance : distance + 1
      end
    end
    
    private
    
    def strand_matches_at_index?(strand, char, index)
      res = strand[index] == char || strand[index] == nil
    end
    
  end
  
end
