def compute(seq1, seq2)
        nb_diffs = 0
        seq1.split("").each_index { |idx| nb_diffs += 1 if seq1[idx] != seq2[idx] }
        nb_diffs
    end