class Hamming
  
  class << self
    
    def compute(strand1, strand2)
      strand1.chars.each_with_index.inject(0) do |distance, (char,index)|
        strand_matches_at_index?(strand2, char, index) ? distance : distance + 1
      end
    end
    
    private
    
    def strand_matches_at_index?(strand, char, index)
      strand[index] == char || strand[index].nil?
    end
    
  end
  
end
