class Hamming
  def compute(str1,str2)
    a,b = str1.chars, str2.chars
    return a.reject.with_index { |x,idx| b[idx] == x }.count
  end
end
