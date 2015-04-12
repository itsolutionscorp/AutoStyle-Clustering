class Hamming
    def compute(strand1, strand2)
        count = 0
        strand1.split('').each_with_index{|a, i| count += 1 if a != strand2[i] && strand2[i] }
        count
    end
end
