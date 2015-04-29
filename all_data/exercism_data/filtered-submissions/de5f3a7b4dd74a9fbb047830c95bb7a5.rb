def compute(x, y)
    counter, diff = 0, 0
    length = [x.size, y.size].min

    until counter == length
      if x[counter] != y[counter]
        diff += 1
      end
      counter += 1
    end

    diff
  end