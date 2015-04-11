class Hamming
    def self.compute(a, b)
        ary_a = a.split("")
        ary_b = b.split("")
        distance = 0
        ary_a.each_with_index {|value, index|
            if value != ary_b[index]
                distance+=1
            end
        }
        distance
    end
end
