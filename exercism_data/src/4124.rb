def compute(sequence_1, sequence_2)
    distance = 0
    difference = (sequence_1.length - sequence_2.length).abs.to_i

    0.upto(sequence_1.length) do |base|
      sequence_1[base] == sequence_2[base] || distance += 1
    end
    distance - difference
  end