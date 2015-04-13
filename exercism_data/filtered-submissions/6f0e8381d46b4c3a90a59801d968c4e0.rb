def compute(seq1, seq2)
    (0..seq1.length).inject(0) do |count, index|
      seq1[index] != seq2[index] ? count + 1 : count
    end
  end