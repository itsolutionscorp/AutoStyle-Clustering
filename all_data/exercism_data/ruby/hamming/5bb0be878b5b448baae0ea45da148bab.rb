class Hamming
    def self.compute(strand1, strand2)
        @diff = 0

        strand1.chars.each_with_index do |v, i|
            if v != strand2[i]
                @diff += 1
            end
        end

        return @diff
    end
end
