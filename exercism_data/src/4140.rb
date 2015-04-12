def compute(arg1, arg2)

    arr1 = arg1.split("")
    arr2 = arg2.split("")

    counter = 0
    arr1.each_with_index do |x, i|
      if x != arr2[i]
        counter += 1
      end
    end
    return counter
  end
end

# if (arg1 == 'AT' and arg2 == 'CT')
#    arr1 = arg1.split("")
#    arr2 = arg2.split("")
#
#    (arr1 + arr2).reject { |x| x == "T" }
#