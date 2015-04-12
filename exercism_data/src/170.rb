def compute(first_string, second_string)
    first_string_array = first_string.split(//)
    second_string_array = second_string.split(//)
    index = 0
    hamming_distance = []
    first_string_array.each do |i| 
      if (i != second_string_array[index] || nil ) && (second_string_array[index] != nil)
        hamming_distance << i
      end
      index += 1
    end
    hamming_distance.count
  end