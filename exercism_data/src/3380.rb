def compute(a,b)
    a = a.split('')
    b = b.split('')
    distance = 0
    points = [a.length, b.length].min

    points.times { a.shift == b.shift ? nil : distance += 1 }
    distance
  end