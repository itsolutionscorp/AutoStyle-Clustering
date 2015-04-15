def compute(inputA, inputB)

    difference_counter = 0


    inputA = inputA.slice(0, inputB.length)

    inputA_array = inputA.chars
    inputB_array = inputB.chars

    inputA_array.each_index { |index_num|
      if (inputA_array[index_num] != inputB_array[index_num])
        difference_counter+=1
      end
    }

    difference_counter

  end