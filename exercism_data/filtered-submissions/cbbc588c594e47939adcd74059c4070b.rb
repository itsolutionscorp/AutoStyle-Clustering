def compute(left, right)
    count, index = 0, 0

    shortest_length = left.length < right.length ?
                        left.length :
                        right.length

    while(index < shortest_length) do
      different_letters = (left[index] != right[index])
      count += 1 if different_letters
      index += 1
    end

    return count
  end