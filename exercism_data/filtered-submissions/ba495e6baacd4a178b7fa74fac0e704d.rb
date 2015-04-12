def compute str1, str2
    tmp = str1.length > str2.length ? str2 : str1
    (1..tmp.length).map do |i|
      str1[i-1] == str2[i-1] ? 0 : 1
    end.inject(0, :+)
  end