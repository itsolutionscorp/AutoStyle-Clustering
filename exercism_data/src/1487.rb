def compute(str1, str2)
    distance = 0
    str2.length.times do |num|
      distance += ( str1[num] == str2[num] ? 0 : 1 )
    end
    distance
  end