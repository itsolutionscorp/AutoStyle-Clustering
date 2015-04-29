def compute(sequence1, sequence2)
    shortest = [sequence1.length, sequence2.length].min
    (0...shortest).count { |i| sequence1[i] != sequence2[i] }
  end