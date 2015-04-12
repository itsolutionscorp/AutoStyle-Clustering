def compute(first_sequence, second_sequence)
    distance = 0
    first_sequence.split('').each_with_index do |l, i|
      break if second_sequence[i].nil?
      distance += 1 if second_sequence[i] != l
    end
    distance
  end