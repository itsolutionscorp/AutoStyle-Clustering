def compute(p1, p2)
    p2_to_array = p2.split('')
    p1_to_array = p1.split('')
    p1_to_array.each_with_index.inject(0){|diff, (item, index)| diff += (item <=> p2_to_array[index]).abs}
  end