def compute(string1, string2)
    first_string = string1.chars
    second_string = string2.chars
    count = 0

    for i in first_string
      if first_string[i] == second_string[i]
        count += 1
      end
    end
    return count
  end