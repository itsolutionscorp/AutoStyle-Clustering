def compute str1, str2
    arr1, arr2 = str1.split(''), str2.split('')

    distance = 0
    arr1.size.times do |x|
      return distance if arr2.size == x
      distance += 1 if arr1[x] != arr2[x]
    end
    distance
  end