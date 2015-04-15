def compute(first,second)
    length = [first.length, second.length].min
    firstArr = first[0,length].chars
    secondArr = second[0,length].chars
    charPairs = firstArr.zip(secondArr)
    charPairs.count { |x,y| x != y }
  end