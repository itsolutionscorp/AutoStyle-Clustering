def compute strand1, strand2
    i = 0
    count = 0
    
    strand1.each_char do |c|
      if ( c != strand2[i] )
        count += 1
      end
      i += 1
    end
    
    return count
    
  end