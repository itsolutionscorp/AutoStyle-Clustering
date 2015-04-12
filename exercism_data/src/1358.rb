def compute (a, b)
    a = a.chars
    b = b.chars
    a = a.slice(0, b.count) if a.count > b.count
    b = b.slice(0, a.count) if b.count > a.count
    distance = 0
    a.zip(b).map { |x, y| distance += 1 if x != y }
    return distance
  end