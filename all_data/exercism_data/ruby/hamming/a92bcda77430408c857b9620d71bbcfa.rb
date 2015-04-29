class Hamming

    def self.compute(s1, s2)
        s1 = s1.split('')
        s2 = s2.split('')
        s1 = s1[0..s2.length-1] if s1.length > s2.length
        s1.zip(s2).delete_if { |x| x[0] == x[1] }.length
    end

end

# length [ True | (c1, c2) <- (zip s1 s2::[(Char, Char)]), c1 /= c2 ]
