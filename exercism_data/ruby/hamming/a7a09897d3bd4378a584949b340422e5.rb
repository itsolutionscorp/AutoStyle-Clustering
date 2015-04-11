class Hamming

    def self.compute(x, y)
        x.chars.zip(y.chars).select{ |a,b| a != b }.count
    end
end
