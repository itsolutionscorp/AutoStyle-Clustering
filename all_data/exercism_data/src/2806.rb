def compute(x,y)
    x_arr = x.split(//)
    y_arr = y.split(//)

    ctr = 0

    x_arr.each_with_index do |i,j|
      if y_arr[j] != i
        ctr += 1
      end
    end

    ctr
  end