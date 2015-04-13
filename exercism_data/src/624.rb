def compute(x, y)




    arr1 = x.split(//)
    arr2 = y.split(//)

    count = 0

    test = arr1.length

    for i in 0..test do
      if arr1[i] != arr2[i]
        count += 1
      end
    end

    return count

  end