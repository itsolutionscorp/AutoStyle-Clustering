def compute(string1,string2)
    hamming_distance = 0

    string1_array = string1.split('')
    string2_array = string2.split('')

    string1_array.each_with_index do |letter,index|
      if string2_array[index] && letter != string2_array[index]
        hamming_distance +=1
      end
    end

    hamming_distance
  end