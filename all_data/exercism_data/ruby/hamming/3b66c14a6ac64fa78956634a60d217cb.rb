class Hamming
    def self.compute(strand1, strand2)
        if strand1.length != strand2.length then
            raise "Error: strand length must be equal"
        end
        (0..strand1.length).reject {|i| strand1[i] == strand2[i]}.length
    end
end
