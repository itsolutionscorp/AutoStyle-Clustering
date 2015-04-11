class Hamming
    def self.compute(a,b)
        count=0
        for pos in 0..a.length - 1
            count += 1 if a[pos]!=b[pos]
        end
        count
    end
end
