def compute(first, second)

    firstArr = first.split('')
    secondArr = second.split('')
    counter = 0


    firstArr.each_with_index do |letter, i|
      counter += 1 if letter != secondArr[i]
    end


    counter
  end