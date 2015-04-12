class Hamming
  def compute(string_1, string_2)
    string_1_array = string_1.split("")
    string_2_array = string_2.split("")

    hamming_distance = 0

    if string_1_array.length == string_2_array.length
      for i in 0..(string_1_array.length - 1)
        if string_1_array[i] != string_2_array[i]
          hamming_distance +=1
        end
      end
      hamming_distance
    elsif string_1_array.length > string_2_array.length
      for i in 0..(string_2_array.length - 1)
        if string_1_array[i] != string_2_array[i]
          hamming_distance +=1
        end
      end
      hamming_distance
    elsif string_1_array.length < string_2_array.length
      for i in 0..(string_1_array.length - 1)
        if string_1_array[i] != string_2_array[i]
          hamming_distance +=1
        end
      end
      hamming_distance
    end
  end
end
