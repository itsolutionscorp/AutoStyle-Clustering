def compute(x,y)
    x_arr = x.split(//)
    y_arr = y.split(//)

    ctr = 0
    idx = 0

    x_arr.each do |i|
      if y_arr[idx] != i
        ctr = ctr + 1
      end

      idx = idx + 1
    end

    ctr
  end