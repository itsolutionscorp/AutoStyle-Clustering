class Hamming
    def Hamming.compute (a, b)
        diff = 0
        for i in 0 ..  shortest_index(a, b)
            diff += 1 if a[i] != b[i]
        end

        return diff
    end

    private
    def Hamming.shortest_index (a, b)
        a.length < b.length ? a.length - 1 : b.length - 1
    end
end
