def compute(first, second)
    first = first.scan /\w/
    second = second.scan /\w/
    max_size = [first.length, second.length].min
    total = 0

    first.take(max_size).zip(second.take(max_size)).map { |x, y| total += (x <=> y).abs }
    total
  end