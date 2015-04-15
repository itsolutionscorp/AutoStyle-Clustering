def compute(seq1, seq2)
    @count = 0

    seq1, seq2 = seq1.upcase, seq2.upcase

    min = [seq1.length, seq2.length].min
    if min > 0
      for j in 0..min-1

        if seq1[j] != seq2[j]
          @count += 1
        end
      end
    end
    return @count
  end