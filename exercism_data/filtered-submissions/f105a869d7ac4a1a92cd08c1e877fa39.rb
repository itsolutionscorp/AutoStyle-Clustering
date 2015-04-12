def compute(sequence_a, sequence_b)
    length = [sequence_a.length, sequence_b.length].min
    distance = 0
    0.upto(length - 1) do |i|
      distance += 1 unless sequence_a[i] == sequence_b[i]
    end
    distance
  end