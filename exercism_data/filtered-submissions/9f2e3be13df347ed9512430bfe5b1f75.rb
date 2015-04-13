def compute(first, second)
    first.chars.each_with_index.select { |c, i| c != second[i] }.count
  end