def compute(first, second)
    0.upto(first.size - 1).inject(0) do |count, index|
      first[index] != second[index] ? count += 1 : count
    end
  end