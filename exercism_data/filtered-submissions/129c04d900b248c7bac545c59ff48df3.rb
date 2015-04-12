def compute(inputA, inputB)

    difference_counter = 0
    # position_counter = 0 
    
    [inputA.length, inputB.length].min.times do |position_counter|
      if (inputA[position_counter] != inputB[position_counter])
        difference_counter+=1
      end
      position_counter +=1
    end

    difference_counter   

  end