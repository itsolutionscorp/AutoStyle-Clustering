class Hamming
    def compute(a, b)
        pairs = a.chars.to_a.zip(b.chars.to_a)

        pairs.count do |pair|
            next unless pair[0] && pair[1]
            pair[0].upcase != pair[1].upcase
        end
    end
end
