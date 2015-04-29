def compute(string_a, string_b)
        amount = 0
        max_length = [string_a.length, string_b.length].min-1
        0.upto(max_length) do |i|
            amount += 1 unless string_a[i] == string_b[i]
        end
        amount
    end