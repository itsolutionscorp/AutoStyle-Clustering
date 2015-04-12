def compute(string1, string2)
    string1.chars.zip(string2.chars).count { |char1, char2| char1 != char2 }
  end