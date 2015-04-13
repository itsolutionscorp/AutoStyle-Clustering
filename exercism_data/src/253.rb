def compute(a, b)
    distance = 0
    index = 0

    while true
      unless a[index] && b[index]
        return distance
      end

      distance = distance + 1 if a[index] != b[index]

      index = index + 1
    end
  end