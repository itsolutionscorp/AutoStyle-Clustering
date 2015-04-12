def compute(first, second)
    # Variables
    firstArr = first.split('')
    secondArr = second.split('')
    counter = 0

    # Iterate
    firstArr.each_with_index do |letter, i|
      counter += 1 if letter != secondArr[i]
    end

    # Output
    counter
  end