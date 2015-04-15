def compute(arg1, arg2)
    arg1.each_char.with_index.count {|char, index| char != arg2[index]}
  end