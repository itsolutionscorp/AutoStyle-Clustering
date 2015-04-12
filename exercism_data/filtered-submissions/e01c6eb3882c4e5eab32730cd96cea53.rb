def compute( s1, s2 )
        distance = 0
        for i in 0..s1.length
            distance = distance + 1 if ( s1[i] != s2[i] )
        end
        distance
    end