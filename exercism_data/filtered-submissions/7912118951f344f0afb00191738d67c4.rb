def compute(seq1, seq2)
    @count = 0
    # Make sure the sequences are all capitalized.
    seq1, seq2 = seq1.upcase, seq2.upcase
    # Find the smaller of the two sequences
    min = [seq1.length, seq2.length].min
    if min > 0
      for j in 0..min-1
        # If the letters of the sequences dont match, add to the count.
        if seq1[j] != seq2[j]
          @count += 1
        end
      end
    end
    return @count
  end