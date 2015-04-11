class Hamming
    def self.compute a, b
        [a.size, b.size].min.times.count{ |i| a[i] != b[i] }
    end
end
