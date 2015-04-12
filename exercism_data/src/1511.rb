def compute(a, b)
    min = [a.length, b.length].min
    c = a.chars.first(min).zip(b.chars.first(min))
    c.inject(0) {|diff, i| diff += 1 if i.uniq.size == 2; diff }
  end