def compute string1, string2
      min_length = [string1.length, string2.length].min
      hamming_distance = string1.chars.take(min_length).zip(string2.chars).count { |a, b| a != b }
    end