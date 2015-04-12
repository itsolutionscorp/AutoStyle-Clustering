def compute (a, b)
    diff = 0
    value = [a.length(),b.length()].min
    (0...value).each { |index| diff += 1 if a[index] != b[index] }
    diff
  end