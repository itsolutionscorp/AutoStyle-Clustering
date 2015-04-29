class Hamming
    def self.compute(first_strand, second_strand)
        return first_strand.chars.zip(second_strand.chars).count { |x, y| !x.nil? and !y.nil? and x != y }
    end
end
