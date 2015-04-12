def compute(a, b)
    a_array = a.chars
    b_array = b.chars
    distance = 0

    a_array.each_with_index do |item, index|
      distance += 1 if item != b_array[index]
    end

    distance
  end