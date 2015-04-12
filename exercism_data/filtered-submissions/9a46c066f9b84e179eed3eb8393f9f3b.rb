def compute(arg1, arg2)
      arg1.chars.zip(arg2.chars).reduce(0) do |a, e|
        e[0] != e[1] ? a + 1 : a
      end
    end