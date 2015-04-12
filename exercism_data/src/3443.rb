def compute(first_string, second_string)
    first_string, second_string = first_string.split(""), second_string.split("")

    sequences = first_string.zip(second_string).reject {|sequence| sequence.include?(nil)}

    sequences.inject(0) {|sum, sequence| (sequence[0] != sequence[1])? sum += 1 : sum }
  end