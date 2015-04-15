def compute(seq1, seq2)
    hamming_count = 0



    seq1.length.times do |index|
      hamming_count += 1 if seq1[index] != seq2[index]
    end
    hamming_count
  end