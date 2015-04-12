module Hamming
    def Hamming.compute strand_a, strand_b
        strand_a.each_char.zip(strand_b.each_char).count do |x, y|
            y && x != y
        end
    end
end
