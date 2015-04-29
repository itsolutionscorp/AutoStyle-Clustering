class Hamming
    def self.compute( s1, s2 )
        (0..s1.length).count{ |x| s1[x] != s2[x] }
    end
end
