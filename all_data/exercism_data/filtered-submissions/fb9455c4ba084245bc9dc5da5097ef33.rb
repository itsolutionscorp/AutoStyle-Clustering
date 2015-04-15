def compute(arg1, arg2)


    ham = 0
    arg1.length.times do |count|
      if arg1[count] != arg2[count]
        ham += 1
      end
    end
    return ham
  end