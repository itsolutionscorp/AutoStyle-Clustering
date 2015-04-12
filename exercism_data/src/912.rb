class Hamming
    def compute(a, b)
        a.chars.zip(b.chars).count do |string_a, string_b|
            string_b != nil && string_a != string_b
        end
    end
end
