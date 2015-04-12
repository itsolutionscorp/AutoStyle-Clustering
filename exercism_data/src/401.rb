def compute(string_1, string_2)
    string_1.chars.zip(string_2.chars)
      .select {|char_1, char_2| char_1 != char_2}
      .length
  end
end