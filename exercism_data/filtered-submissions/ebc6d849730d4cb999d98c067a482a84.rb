def compute(a, b)
    count = 0
    a.chars.each_index {|i| count += 1 if (a[i] != b[i]) && !a[i].nil? && !b[i].nil?}
    count
  end