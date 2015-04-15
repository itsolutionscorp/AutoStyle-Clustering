def compute(first, second)
    first_array = first.split(//)
    second_array = second.split(//)
    count = 0
    for i in 0..first_array.count - 1
      if first_array[i] != second_array[i]
        count = count + 1
      end
    end 
    return count
  end