def compute(str_1, str_2)

    str_1.length.times.inject(0) do |dist, index|
      str_1[index] != str_2[index] ? dist += 1 : dist
    end
  end