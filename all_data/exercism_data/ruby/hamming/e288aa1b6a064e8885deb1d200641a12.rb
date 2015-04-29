class Hamming
  class << self
    def compute (orig_strand, permut_strand)
      if orig_strand.length <= permut_strand.length
        short = orig_strand
        long = permut_strand
      else
        short = permut_strand
        long = orig_strand
      end  
      
      count_diff(short, long)
    end

    def count_diff (short, long)
      diff = 0
      short.length.times { |i|
         diff += 1 unless short[i] == long[i] 
      }
      diff
    end
  end
end
