def compute arg1, arg2
    a = arg1.chars
    arg2.chars.each_with_index.map{|x,i| x != a[i]}.select{|x|x}.length
  end