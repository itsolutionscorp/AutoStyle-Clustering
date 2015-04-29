def compute(string_1, string_2)
    string_1_array = string_1.split("")
    string_2_array = string_2.split("")

    return (string_1_array - string_2_array).length
  end