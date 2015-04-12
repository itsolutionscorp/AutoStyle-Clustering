def compute sequence1, sequence2
    length = sequence1.length < sequence2.length ? sequence1.length : sequence2.length
    ham = 0
    for i in 0..length-1
      ham += 1 unless sequence1[i] == sequence2[i]
    end                                     
    ham
  end