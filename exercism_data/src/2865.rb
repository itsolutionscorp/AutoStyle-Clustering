class Hamming

    def compute(x, y)
        x.chars.zip(y.chars).select{ |a,b| a != b }.count
    end
end
