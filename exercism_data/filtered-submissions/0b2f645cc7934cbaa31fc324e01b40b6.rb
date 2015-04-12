def compute(first_sequence, second_sequence)
    first_sequence.chars.zip(second_sequence.chars).reject{|a| a[0] == a[1]}.count
  end