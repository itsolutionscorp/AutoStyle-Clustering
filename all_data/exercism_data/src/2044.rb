def compute(a, b)
    distance = 0

    if a.size < b.size
      shortest = a
      other = b
    else
      shortest = b
      other = a
    end

    while !shortest.empty?
      sChar = shortest.slice!(0)
      oChar = other.slice!(0)

      if (sChar != oChar)
        distance = distance + 1
      end
    end

    distance
  end