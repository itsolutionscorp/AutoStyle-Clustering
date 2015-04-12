def compute(str1, str2)
    hamming_distance = 0

    if str1.length < str2.length
      count = str1.length
    else
      count = str2.length
    end

    count.times do |point|
      hamming_distance += 1 if str1[point] != str2[point]
    end

    hamming_distance
  end