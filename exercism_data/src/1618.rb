def compute(string1, string2)
    string_1_array = []
    string_2_array = []

    string1.each_char{|a| string_1_array << a}
    compare1 = string_1_array.map.with_index{|value, index| [index, value] }

    string2.each_char{|a| string_2_array << a}
    compare2 = string_2_array.map.with_index{|value, index| [index, value] }

    string1.length - ((compare1 & compare2).count)
  end