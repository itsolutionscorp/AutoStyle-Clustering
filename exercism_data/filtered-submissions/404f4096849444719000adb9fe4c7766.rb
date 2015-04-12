def compute(first, second)
    distance = 0
    max = [ first.size, second.size ].min - 1

    0.upto(max) do |i|
      distance +=1 if first[i] != second[i]
    end

    return distance
  end