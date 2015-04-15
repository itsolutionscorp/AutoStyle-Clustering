class Hamming

  def self.compute(x, y)
    if x == y
      counter = 0
    else
      first_string = x.split(//)
      second_string = y.split(//)
      counter = 0
      index = 0
      while index < [(first_string.count),(second_string.count)].min
        if first_string[index] != second_string[index]
          counter += 1
          index += 1
        else
          index +=1
        end
      end
    end
      p counter
  end

end
