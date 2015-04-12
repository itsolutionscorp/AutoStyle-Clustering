class Hamming
  def compute(str1, str2)
    str1.length.times.inject(0) { |sum, e| sum += (str1[e]<=>str2[e]).abs }
  end
end
