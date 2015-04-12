def compute str1, str2
    distance = 0
    str1.size.times do |x|
      return distance if str2.size == x
      distance += 1 if str1[x] != str2[x]
    end
    distance
  end