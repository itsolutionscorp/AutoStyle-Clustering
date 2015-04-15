class Hamming
    # Computes the hamming distance of two values
    def self.compute(a, b)
        min_length = [a.size, b.size].min
        (0 ... min_length).count { |index| a[index] != b[index] }
    end
end
