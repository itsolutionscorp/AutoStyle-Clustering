def compute(arg1, arg2)
    # we add one to the hamming count where the index at arg1 is different then arg2
    # we can go through the first array
    # then we can check at that index's value and evaluate if it is not equal to the array2 value at that index
    if arg1.length >= arg2.length
      long = arg1.chars
      short = arg2.chars
    else
      long = arg2.chars
      short = arg1.chars
    end
    short.zip(long).select { |array| array[0] != array[1] }.count
  end