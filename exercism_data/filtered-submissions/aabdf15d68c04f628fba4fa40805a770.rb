def compute(str1, str2)
    distance=0
    if str1.length > str2.length
      temp=str1
      str1=str2
      str2=temp
    end
    str1.length.times do |i|
      if str1[i] != str2[i]
        distance += 1
      end
    end
    distance
  end