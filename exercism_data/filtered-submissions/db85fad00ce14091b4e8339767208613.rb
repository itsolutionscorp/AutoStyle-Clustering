def compute(arg1, arg2)


    ham = 0
    arg1.length.times do |index|
      if arg1[index] != arg2[index]
        ham += 1
      end
    end
    return ham
  end