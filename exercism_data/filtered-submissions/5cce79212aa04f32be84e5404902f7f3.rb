def compute(first, second)
    arr1 = first.chars
    arr2 = second.chars

    length = 0

    arr1.size.times do |i|
      length += 1 unless arr1[i] == arr2[i]
    end

    length
  end