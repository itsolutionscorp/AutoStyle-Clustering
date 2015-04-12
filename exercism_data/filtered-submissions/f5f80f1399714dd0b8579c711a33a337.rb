def compute(seq1, seq2)
    count = 0
    seq1.chars.map!.with_index do |n, i|
      count += 1 if n != seq2[i]
    end
    count
  end