def compute(str1, str2)
    dist = 0

    until str1.empty? || str2.empty?
      break if str1 == str2
      dist += 1 if str1.slice!(0) != str2.slice!(0)
    end

    dist
  end