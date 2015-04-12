def compute(str1, str2)
    (0...[str1.length, str2.length].min).count do |index|
      str1[index] != str2[index]
    end
  end