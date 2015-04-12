class Hamming
  def compute(n1, n2)
    arr1 = n1.split('')
    arr2 = n2.split('')

    diff_count  = 0
    arr_count   = 0

    while arr_count < arr1.length  
      diff_count += 1 if arr1[arr_count] != arr2[arr_count]
      arr_count += 1
    end
    diff_count
  end
end
