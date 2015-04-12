def compute first_sequence, second_sequence
    (0..first_sequence.length).count do |idx|
      first_sequence[idx] != second_sequence[idx]
    end
  end
end