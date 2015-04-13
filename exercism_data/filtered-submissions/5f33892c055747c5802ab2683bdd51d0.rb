def compute(first,second)


    result = 0
    (0..first.length-1).each do |index|
      if  first[index] != second[index]
        result += 1
      end
    end
    return result
  end