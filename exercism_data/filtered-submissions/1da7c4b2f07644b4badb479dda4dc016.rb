class Hamming
  def compute(strand1, strand2)
    count = 0
    
    if(strand1.length <= strand2.length)
      short_strand = strand1
      long_strand = strand2
    else
      short_strand = strand2
      long_strand = strand1
    end    

    short_strand.each_char.with_index(0) do |c,i|
      if long_strand[i] != c
        count = count + 1
      end
    end
    return count   
  end
end
