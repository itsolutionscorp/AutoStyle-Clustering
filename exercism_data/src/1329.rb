class Hamming
  def compute(str1, str2)
    valid_length = [str1.length, str2.length].min
    str1 = str1[0..valid_length - 1]
    str2 = str2[0..valid_length - 1]
    str1.chars.zip(str2.chars).count { |c| c[0] != c[1] }
  end
end
