def compute(arg1, arg2)
    length1 = arg1.length
    count = 0

    length1.times do |i|

      if arg1[i] == arg2[i]
        count
      else
        count+=1
      end
    end # length1.times
    count
  end # self.compute