def compute arg1, arg2
    if (arg1.length != arg2.length)
      return 'Error: the two arguments must be of the same length'
    else
      arg1 = arg1.split('')
      arg2 = arg2.split('')
      diff_count = 0
      arg1.each_index do |i|
        if (arg1[i] != arg2[i])
          diff_count += 1;
        end
      end
      return diff_count
    end
  end