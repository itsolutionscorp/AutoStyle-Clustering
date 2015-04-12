def compute(a, b)
    diff = 0
    array_a = a.split("")
    array_b = b.split("")
    length = array_a.length
    (0..length).each do |n|
      diff += 1 if array_a[n] != array_b[n]
    end
    return diff
  end