class Hamming
  def compute arg1, arg2
    arg2.chars.keep_if.with_index{|x,i| x != arg1[i]}.length
  end
end
