def compute( s1, s2 )
        raise ArgumentError, 'Strings must be the same length' unless s1.length == s2.length
        s1.length.times.count{ |x| s1[x] != s2[x] }
    end