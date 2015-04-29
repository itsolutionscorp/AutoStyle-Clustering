def compute(string_one, string_two)
    combinations = string_one.chars.zip(string_two.chars)

    combinations.count { |pair| pair[0] != pair[1] }
  end