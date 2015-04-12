def compute(string1, string2)
    chars1 = string1.chars
    chars2 = string2.chars

    merge = string1 <= string2 ? chars1.zip(chars2) : chars2.zip(chars1)

    merge.count { |char1, char2| char1 != char2 }
  end