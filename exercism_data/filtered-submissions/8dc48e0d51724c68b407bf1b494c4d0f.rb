class Hamming
    def compute(s1, s2)
        a1 = s1.split('')
        a2 = s2.split('')

        return 0 if a1.eql?(a2)

        a1.each_with_index.reduce(0) do |base, (curr, index)|
            a2[index].eql?(curr) ? base : base + 1
        end
    end
end
