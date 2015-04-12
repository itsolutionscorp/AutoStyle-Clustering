def compute(seq1, seq2)
    count = 0
    if seq1.length == seq2.length
      seq1.chars.map!.with_index do |n, i|
        if n != seq2[i]
          count += 1
        end
      end
    end
    count
  end