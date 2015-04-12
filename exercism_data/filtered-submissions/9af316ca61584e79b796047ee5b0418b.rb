def compute(first, second)
    return 0 if first == second

    min_length = [first, second].map(&:length).min
    (0...min_length).count { |i| first[i] != second[i] }
  end