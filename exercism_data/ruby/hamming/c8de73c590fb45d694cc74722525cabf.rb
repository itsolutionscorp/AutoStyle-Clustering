class Hamming
    def self.compute(dn_a, dn_b)
        ham = 0
        # for i in min_len
        #   if dn_a[i] != dn_b[i]
        #     ham += 1
        #   end
        # end
        min_len(dn_a, dn_b).times { |i| ham += 1 if dn_a[i] != dn_b[i] }
        ham
    end

    private
    # returns the min length of two strings
    def self.min_len(a, b)
        a.length <= b.length ? a.length : b.length
    end
end
