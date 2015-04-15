def compute(str1, str2)
    iterations = str1.length <= str2.length ? str1.length : str2.length

    total = 0
    iterations.times do |i|
      total += 1 if str1[i] != str2[i]
    end
    total
  end