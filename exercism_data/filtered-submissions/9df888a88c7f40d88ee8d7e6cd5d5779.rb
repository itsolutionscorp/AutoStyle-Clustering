class Hamming
    def compute(a, b)
        [a.length, b.length].min.times.count { |i| a[i] != b[i] }
    end
end
