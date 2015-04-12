class Hamming
    def compute( s1, s2 )
        raise ArgumentError, 'Strings must be the same length' unless s1.length == s2.length
        (0..s1.length-1).count{ |x| s1[x] != s2[x] }
    end
end
