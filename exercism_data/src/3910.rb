def compute( seq1, seq2 )
    count = [ seq1.length, seq2.length ].min
    distance = 0
    
    for i in 0...count
      if( seq1[i] != seq2[i] )
        distance += 1
      end
    end
    
    distance
  end