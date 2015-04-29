def compute(x, y)
    difference = 0
    for i in 0..x.length-1
      unless x[i] == y[i]
        difference += 1
      end
    end
    difference
  end