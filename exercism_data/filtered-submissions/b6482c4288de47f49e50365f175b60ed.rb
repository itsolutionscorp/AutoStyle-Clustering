def compute(str_1, str_2)
    total = 0

    length = str_1.length > str_2.length ? str_2.length : str_1.length

    (0...length).each do |index|
      total += 1 if str_1[index] != str_2[index]
    end

    total
  end