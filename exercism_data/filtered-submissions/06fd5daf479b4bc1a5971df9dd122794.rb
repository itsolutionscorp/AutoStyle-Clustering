class Hamming
  def compute( acid1, acid2 )
    i = 0
    distance = 0
    
    while( acid1[i] and acid2[i] )
      if( acid1[i] != acid2[i] )
        distance += 1
      end
      i += 1
    end
    
    distance
  end
end
