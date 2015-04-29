def compute(str1, str2)
    i = 0
    ham = 0
    str1.length < str2.length ? lon = str1 : lon = str2
    while i < lon.length
      str1[i] != str2[i] ? ham += 1 : ham
      i += 1
    end
    ham
  end