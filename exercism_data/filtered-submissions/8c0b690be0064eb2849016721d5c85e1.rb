def compute(first, second)
    (0..first.length).count {|i| first[i] != second[i]}
  end