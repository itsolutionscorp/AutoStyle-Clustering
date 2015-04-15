def compute(x,y)
    sum = 0
    x.split("").each_with_index do |letter, i|
      if !(x[i] == y[i])
        sum += 1
      end
    end
    sum
  end