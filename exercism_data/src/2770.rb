class Hamming
  def compute( acid1, acid2 )
    count = [ acid1.length, acid2.length ].min
    distance = 0
    
    for i in 0...count
      if( acid1[i] != acid2[i] )
        distance += 1
      end
    end
    
    distance
  end
end
