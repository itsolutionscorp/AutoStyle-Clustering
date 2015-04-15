def compute(seq1, seq2)
        dist = 0
        l = seq1.length < seq2.length ? seq1.length : seq2.length
        0.upto (l - 1) do |x|
            dist += 1 if seq1[x].chr != seq2[x].chr
        end
        dist
    end