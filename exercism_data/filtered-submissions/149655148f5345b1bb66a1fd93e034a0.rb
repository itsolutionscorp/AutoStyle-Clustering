def compute(input1, input2)
    minlength = [input1.length, input2.length].min
    i = 0
    result = 0

    while i < minlength do
      if input1[i] != input2[i]
        result = result + 1
      end
      i = i + 1
    end
    return result
  end