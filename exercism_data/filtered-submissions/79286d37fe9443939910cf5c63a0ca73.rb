def compute(inputA, inputB)

    difference_counter = 0

    [inputA.length, inputB.length].min.times do |i|
      if (inputA[i] != inputB[i])
        difference_counter+=1
      end
    end

    difference_counter

  end