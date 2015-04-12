def compute(str1, str2)
    gataca_count = (str1.length <= str2.length) ? str1.length : str2.length
    hamming = 0
    gataca_count.times { |n| hamming += 1 unless str1[n] == str2[n] }
    hamming
  end