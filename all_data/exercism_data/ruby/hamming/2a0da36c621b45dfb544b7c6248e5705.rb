class Hamming

    def self.compute(orig_strand, mutant_strand)
        difference = 0

        shortest_legnth(orig_strand, mutant_strand).times do |point|
            difference += 1 if orig_strand[point] != mutant_strand[point]
        end

        difference
    end

private
    def self.shortest_legnth(str1, str2)
        (str1.length < str2.length)? str1.length : str2.length
    end
end
