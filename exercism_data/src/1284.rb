class Hamming
    def compute(a, b)
        c = a.length < b.length ? a.length : b.length
        result = 0
        c.times do |i|
            result += a[i] != b[i] ? 1 : 0
        end
        result
    end
end
