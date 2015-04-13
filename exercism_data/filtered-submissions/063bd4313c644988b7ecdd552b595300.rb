def compute(first_strand, second_strand)
    length = [first_strand.size, second_strand.size].min
    (0...length).reduce(0) do |error, n|
      error += 1 if (first_strand[n] != second_strand[n])
      error
    end
  end