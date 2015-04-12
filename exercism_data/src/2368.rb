def compute(a, b)
    a = a.split("")
    b = b.split("")
    a.map.with_index { |item, i| item == b[i] ? 0 : 1 }.reduce(:+)
  end