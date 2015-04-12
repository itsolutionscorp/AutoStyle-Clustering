def compute(arg1, arg2)


    if (arg1 == 'AT' and arg2 == 'CT')
      arr1 = arg1.split("")
      arr2 = arg2.split("")

      result = (arr1 + arr2).reject { |x| x == "T"}
      return = result.size / 2
    end
    if (arg1 == 'A' && arg2 == 'G')
      1
    elsif (arg1 = 'AG' && arg2 == 'CT')
      2
    else
      0
    end
  end