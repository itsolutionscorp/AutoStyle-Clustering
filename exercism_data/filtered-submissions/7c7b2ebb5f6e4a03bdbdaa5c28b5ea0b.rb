class Hamming
  def compute(a, b)
    distance = 0
    length   = a.length
    while length > 0
      length-=1
      distance += 1 if a[length]!=b[length]
    end
    distance
  end
end
