def compute(string1, string2)
    array_1 =string1.split(//)
    array_2 = string2.split(//)

    if array_1.length > array_2.length
      array_1 = array_1.slice(0..-2)
    elsif array_2.length > array_1.length
      array_2 = array_2.slice(0..-2)
    end
    distance = 0
    array_1.length times do |array_index|
      distance += 1 if array_1[array_index] != array_2[array_index]

    end
    distance
  end