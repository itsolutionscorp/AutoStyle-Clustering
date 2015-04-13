def compute(str1, str2)
    if str1.length > str2.length
      str1, str2 = str2, str1
    end
    hamming = 0
    (0..(str1.length - 1)).each do |index|
      if str1[index] != str2[index]
        hamming += 1
      end
    end

    hamming
  end