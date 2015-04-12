class Hamming

  def compute(str1, str2)
    str1.chars.zip(str2.chars).reject { |a| a.first == a.last }.count
  end

end
