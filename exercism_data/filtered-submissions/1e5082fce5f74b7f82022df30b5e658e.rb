class Hamming
    def compute(string_a, string_b)
        max_length = [string_a.length, string_b.length].min-1
        (0.upto(max_length)).count do |i|
            string_a[i] != string_b[i]
        end
    end
end
