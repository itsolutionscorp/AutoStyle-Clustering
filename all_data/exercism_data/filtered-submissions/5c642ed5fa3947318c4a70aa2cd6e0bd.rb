def compute(first, second)
    return 0 if first == second

    differences = 0
    min_length = [first, second].map(&:length).min
    0.upto(min_length-1) do |i|
      differences += 1 if first[i] != second[i]
    end
    differences
  end