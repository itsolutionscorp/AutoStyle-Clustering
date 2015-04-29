def compute(first, second)
    counter = 0
    first.length.downto(0) do |i|
      counter += 1 if first[i] != second[i]
    end
    counter
  end