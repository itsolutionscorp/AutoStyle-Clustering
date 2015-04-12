def compute (a, b)
    a = a.chars
    b = b.chars

    distance = 0

    a.each_with_index do |char, index|
      if char == b[index]
        next
      else
        distance += 1
      end
    end

    distance
  end