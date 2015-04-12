class Hamming
    def compute(a, b)
        be = b.each_char
        c = 0
        a.each_char{ |e| c += 1 if e != be.next}
        c
    end
end
