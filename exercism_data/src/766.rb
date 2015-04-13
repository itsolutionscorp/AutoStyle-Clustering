def compute(str1, str2)
      distance = 0
      len = [str1.length, str2.length].min
      (0..len-1).each {|i| distance += 1 unless str1[i] == str2[i] }
      distance
    end