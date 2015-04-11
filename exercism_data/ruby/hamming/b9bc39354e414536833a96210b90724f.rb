class Hamming
    def self.compute(a, b)
        length = [a.length, b.length].min
        dist = 0
        length.times { |i| dist += 1 if a[i] != b[i] }
        dist
    end
end
