def compute(string1, string2)
    min_string_size = [string1.size, string2.size].min
    min_string_size.times.count{|i| string1[i] != string2[i]}
  end