class Hamming
    def compute(strand1, strand2)
        bytes = strand1.bytes.zip(strand2.bytes).reject{|b1,b2| b2.nil?}
        xor = bytes.map{|b1, b2| b1 ^ b2}
        xor.reject! {|c| c == 0}
        xor.size
    end
end
