def compute(str1, str2)
    str1, str2 = str2, str1 if str1.size > str2.size
    str1, str2 = str1.chars, str2.chars
    str1.zip(str2).count {|(c1, c2)| c1 != c2 }
  end