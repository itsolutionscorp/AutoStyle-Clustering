def compute string1, string2
    distance = 0
    short_length = [string1.length, string2.length].min

    short_length.times do |i|
      if string1[i] != string2[i]
        distance += 1
      end
    end
    return distance
  end