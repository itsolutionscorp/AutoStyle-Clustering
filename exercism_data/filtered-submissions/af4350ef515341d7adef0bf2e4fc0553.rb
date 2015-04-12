def compute(first, second)
    distance = 0
    second = second.split('')
    first.split('').each_with_index { |char, idx| distance += 1 if char == second[idx] }
    first.size - distance
  end