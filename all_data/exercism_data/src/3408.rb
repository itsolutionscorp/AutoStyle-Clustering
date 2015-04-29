def compute(first, second)
    min = [first.length, second.length].min
    first[0, min].unpack('C*')
                  .zip(second[0, min]
                  .unpack('C*'))
                  .map {|a| a.first ^ a.last}
                  .reject {|x| x == 0}
                  .length
  end