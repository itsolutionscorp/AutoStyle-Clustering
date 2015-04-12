def compute(str1, str2)
    (0..str1.length-1).map do |n|
      (str1[n] <=> str2[n]).abs
    end.reduce(&:+)
  end