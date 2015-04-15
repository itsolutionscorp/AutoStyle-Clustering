def compute(a, b)
    ac = a.chars
    bc = b.chars
    ac, bc = bc, ac if a.length > b.length
    sum = 0

    ac.zip(bc) { |x, y| sum += 1 if x != y }
    sum
  end