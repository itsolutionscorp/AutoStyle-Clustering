class Hamming
    def self.compute(a, b)
        return nil if a.length != b.length
        i = 0
        h = 0
        a.each_char {|c|
            if c != b[i]
                h += 1
            end
            i += 1
        }
        return h
    end
end
