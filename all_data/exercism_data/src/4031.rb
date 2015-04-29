def compute(arg1,arg2)
    arr1 = arg1.split('')
    arr2 = arg2.split('')
    num_of_false = 0
    arr1.length.times do |n|
      num_of_false += 1 if arr1[n] != arr2[n]
    end
    return num_of_false
  end