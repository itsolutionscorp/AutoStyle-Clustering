class Hamming
  def compute(str1, str2)
    str1.chars.zip(str2.chars).map { |c| c[1] && c[0] != c[1] }.count(true)
  end
end
