class Hamming
    # Computes the hamming distance of two strands
    def self.compute(first_strand, second_strand)
        if first_strand[0] == nil || second_strand[0] == nil
            0
        elsif first_strand[0] != second_strand[0]
            1 + compute(first_strand[1..-1], second_strand[1..-1])
        else
            compute(first_strand[1..-1], second_strand[1..-1])
        end
    end
end
