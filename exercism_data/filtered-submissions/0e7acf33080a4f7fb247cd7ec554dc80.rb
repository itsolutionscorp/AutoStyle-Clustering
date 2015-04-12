def compute str1, str2
    return nil if str1.size != str2.size
    distance = 0
    arr1 = str1.split ''
    arr2 = str2.split ''
    arr1.size.times do |i|
      distance += (arr1[i] != arr2[i] ? 1 : 0)
    end
    distance
  end