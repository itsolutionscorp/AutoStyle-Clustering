def compute first, second
    shorter = [first, second].min_by(&:length)
    (0...shorter.length).select { |i| first[i] != second[i]  }.count
  end