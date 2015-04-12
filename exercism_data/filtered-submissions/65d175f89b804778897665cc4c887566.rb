class Hamming

  def compute(x,y)
    x_arr = x.split(//)
    y_arr = y.split(//)

    ctr = 0

    x_arr.each_with_index do |i,j|
      ctr += 1 unless y_arr[j].eql?(i)
    end

    ctr
  end

end
