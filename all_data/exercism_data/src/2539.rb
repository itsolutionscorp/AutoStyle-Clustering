def compute(str_1, str_2)
    total = 0

    [str_1.length, str_2.length].min.times.each do |index|
      total += 1 if str_1[index] != str_2[index]
    end

    total
  end