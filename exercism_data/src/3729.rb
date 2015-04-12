def compute(str1, str2)
    ary1 = str1.chars
    ary2 = str2.chars
    result = 0
    ary1.length.times do |i|
      result += 1 unless ary1[i] == ary2[i]
    end
    result
  end