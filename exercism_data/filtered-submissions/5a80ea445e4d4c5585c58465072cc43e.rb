def compute(a, b)

    # Find the shorter of the strings
    smallest = [a, b].min_by(&:length)

    # Count the number of character-wise differences between a and b
    smallest.split('').each_index.count { |i| a[i] != b[i] }
  end