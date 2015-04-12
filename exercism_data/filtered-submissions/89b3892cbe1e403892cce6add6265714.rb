def compute(a, b)
    raise DifferingArgumentLength unless a.length == b.length
    (0..a.chars.length - 1).inject(0) { |r, i| r += 1 if a[i] != b[i]; r }