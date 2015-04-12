def compute str1, str2
    ham_dist = 0
    arr1, arr2 = str1.chars, str2.chars
    
    len = [arr1.size, arr2.size].min

    (0...len).each { |i| ham_dist += 1 if arr1[i] != arr2[i] }
    
    ham_dist
  end