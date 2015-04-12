class Hamming
  def compute(first_string, second_string)
    sequences = first_string.chars.zip(second_string.chars).reject {|sequence| sequence.include?(nil)}

    sequences.inject(0) {|sum, sequence| (sequence[0] != sequence[1])? sum += 1 : sum }
  end
end
