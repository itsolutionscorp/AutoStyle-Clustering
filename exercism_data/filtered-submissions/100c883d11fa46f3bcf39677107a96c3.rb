class Hamming

    def compute(a, b)
        array = a.chars.each_with_index.to_a - b.chars.each_with_index.to_a
        return array.length
    end

end
