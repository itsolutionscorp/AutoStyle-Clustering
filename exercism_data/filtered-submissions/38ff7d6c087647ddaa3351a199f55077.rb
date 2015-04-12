class Hamming
  def compute (a, b)
    a = a.chars
    b = b.chars
    a = a.slice(0, b.count)
    distance = 0
    a.zip(b).each { |x, y| distance += 1 if x != y }
    return distance
  end
end
