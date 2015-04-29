def compute(first, second)
    h = 0
    first_array = first.split('')
    second_array = second.split('')

    first_array.each_with_index do |l, i|
      break if i >= second_array.length
      h += 1 if first_array[i] != second_array[i]
    end
    return h
  end