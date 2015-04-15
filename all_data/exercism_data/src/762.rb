def compute(arg1, arg2)



    if arg1.length >= arg2.length
      long = arg1.chars
      short = arg2.chars
    else
      long = arg2.chars
      short = arg1.chars
    end
    short.zip(long).select { |array| array[0] != array[1] }.count
  end