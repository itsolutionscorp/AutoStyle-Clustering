class Hamming

    def compute(a, b)
        count = 0
        strings = [a, b]

        strings.min.size.times do |n|
            a[n]==b[n] ? next : count += 1
        end
        count
    end
end
