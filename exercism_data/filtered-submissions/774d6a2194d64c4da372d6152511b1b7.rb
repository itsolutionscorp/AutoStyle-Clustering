def compute(strandA, strandB)
    strandA.chars.zip(strandB.chars).reduce(0) do |differences, arr|
      arr[0] != arr[1] ? differences + 1 : differences
    end
  end