def compute(arg1, arg2)
    differences = 0
    arg1.each_char.with_index do |c, i|
      differences += 1 if arg1[i] != arg2[i]
    end
    differences
  end