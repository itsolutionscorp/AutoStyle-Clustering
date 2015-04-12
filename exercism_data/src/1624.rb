class Hamming
  def compute(first,second)

    # wanted to use inject but it doesn't handle (0..0) the way I expected
    result = 0
    (0..first.length-1).each do |index|
      if  first[index] != second[index] 
        result += 1
      end
    end
    return result
  end
end
