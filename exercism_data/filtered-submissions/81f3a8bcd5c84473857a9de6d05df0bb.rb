def compute (s1, s2)
    arr1 = s1.upcase.split("")
    arr2 = s2.upcase.split("")
    arr = [arr1,arr2].sort_by{ |s| s.length}


    zipped = arr[0].zip(arr[1])
    zipped.count { |x,y| x != y }
  end