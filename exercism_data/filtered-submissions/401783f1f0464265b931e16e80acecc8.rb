def compute (s_one,s_two)
    hamming_difference = 0
    array1 = s_one.split('')
    array2 = s_two.split('')
    array1.each_with_index do |char, current_pos|
      hamming_difference += 1 if char != array2[current_pos]
    end
    hamming_difference
  end