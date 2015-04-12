def compute(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).reduce(0) do |differences, arr|
      arr[0] != arr[1] ? differences + 1 : differences
    end
  end