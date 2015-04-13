def compute(sequence1, sequence2)
    hi_index = ((l1 = sequence1.length) < (l2 = sequence2.length) ? l1 : l2) - 1






    (0..hi_index).reduce(0) do |distance, index|
      sequence1[index] == sequence2[index] ? distance : distance + 1
    end



  end