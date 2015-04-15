def compute(a, b)


    smallest = [a, b].min_by(&:length)


    smallest.split('').each_index.count { |i| a[i] != b[i] }
  end