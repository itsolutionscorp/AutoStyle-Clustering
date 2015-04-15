class Hamming
  class << self
    def equal_length
      @length=@strand1.length
      return (@length == @strand2.length) ? 1 : 0
    end
    
    def compare_strands
      @hamming_count=0
      i=0 
      @strand1.chars.each do |s1|
        if @strand2[i] != s1
          @hamming_count += 1
        end
        i += 1
      end
    end

    def compute(strand1,strand2)
      @strand1=String.try_convert(strand1)
      @strand2=String.try_convert(strand2)
      if (strand1 == strand2) 
        return 0
      else 
          if equal_length
            compare_strands
          return @hamming_count
        else
          0
        end
      end
    end
  end
end
