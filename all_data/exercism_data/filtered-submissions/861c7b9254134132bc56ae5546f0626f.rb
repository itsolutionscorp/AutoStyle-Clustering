def compute(sequence_a, sequence_b)
    sequence_a.chars.zip(sequence_b.chars).
      delete_if { |a, b| a.nil? || b.nil? }.
      map { |a, b| a == b ? 0 : 1 }.
      reduce(:+)
  end