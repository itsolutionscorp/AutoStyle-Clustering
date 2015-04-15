class Hamming
    def Hamming.different(a, b)
        a != b
    end

    def self.compute(first_strand, second_strand)
        second_strand_enumerator = second_strand.each_char
        count = 0
        first_strand.each_char{ |first_strand_char| count += 1 if different first_strand_char, second_strand_enumerator.next }
        count
    end
end
