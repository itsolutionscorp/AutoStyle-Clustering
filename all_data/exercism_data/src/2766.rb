def compute(str1, str2)
    str1.chars.zip(str2.chars).select do |ch1, ch2| 
      ch1 and ch2 and ch1 != ch2
    end.count
  end