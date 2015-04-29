def compute(first,second)
    length = [first.length, second.length].min
    firstArr = first[0,length].chars
    secondArr = second[0,length].chars
    diffs = firstArr.zip(secondArr).map { |row|
        (row[0] == row[1]) ? 0 : 1
    }
    diffs.reduce(:+) || 0
  end