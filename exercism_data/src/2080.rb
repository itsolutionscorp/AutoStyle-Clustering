class Hamming
    def compute(seq1, seq2)
        nb_diffs = 0
        (0..seq1.length).each { |idx| nb_diffs += 1 unless seq1[idx] == seq2[idx] }
        nb_diffs
    end
end
