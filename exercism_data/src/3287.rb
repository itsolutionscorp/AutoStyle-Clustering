def compute(a, b)
    count = 0
    b.split('').each_with_index do |letter, index|
      count = count + letter.casecmp(a[index]).abs
    end

    count
  end