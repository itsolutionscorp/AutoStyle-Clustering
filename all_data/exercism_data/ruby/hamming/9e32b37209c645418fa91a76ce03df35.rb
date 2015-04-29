class Hamming
  class<<self
    def compute(strand1, strand2)
      first  = position_with_symbols_array(strand1)
      second = position_with_symbols_array(strand2)
      common_size = [ strand1.size, strand2.size ].min - 1
      common_range = 0..common_size
      ( first[common_range] - second[common_range] ).count
    end

    private 
    
    def position_with_symbols_array(str)
      str.chars.each_with_index.to_a.map(&:reverse)
    end
  
  end
  
end
