def compute(seq1, seq2)
      distance = 0
      seq1.chars.zip(seq2.chars) do |a, b|
        distance += 1 unless a == b
      end
      distance
    end