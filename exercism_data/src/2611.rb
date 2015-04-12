def compute(first, second)
    arr1 = first.chars.map(&:ord)
    arr2 = second.chars.map(&:ord)

    length = 0

    arr1.size.times do |i|
      length += 1 unless arr1[i] - arr2[i] == 0
    end

    length
  end